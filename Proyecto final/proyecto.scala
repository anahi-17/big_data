//Contenido del proyecto
//1.- Objectivo: Comparacion del rendimiento siguientes algoritmos de machine learning
// - SVM
// - Decision Three
// - Logistic Regresion
// - Multilayer perceptron    
//Con el siguiente data set: https://archive.ics.uci.edu/ml/datasets/Bank+Marketing 

// Contenido del documento de proyecto final
// 1. Portada
// 2. Indice
// 3. Introduccion
// 4. Marco teorico de los algoritmos
// 5. Implementacion (Que herramientas usaron y porque (en este caso spark con scala))
// 6. Resultados (Un tabular con los datos por cada algoritmo para ver su preformance)
//    y su respectiva explicacion.
// 7. Conclusiones
// 8. Referencias (No wikipedia por ningun motivo, traten que sean de articulos cientificos)
//    El documento debe estar referenciado 

// Nota: si el documento no es presentado , no revisare su desarrollo del proyecto



////////////////////////////////////        S V M       //////////////////////////////////////////////

def svm():Unit={ 
//Cargamos librerias a utilizar
import org.apache.spark.sql.SparkSession
import org.apache.log4j._
import org.apache.spark.mllib.classification.{SVMModel, SVMWithSGD}
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.Pipeline
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.ml.classification.LinearSVC

//Reducción de errores
Logger.getLogger("org").setLevel(Level.ERROR)

//Creamos nuestra sesion de spark
val spark = SparkSession.builder().getOrCreate()

// Cargamos nuestro dataset
val data = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")

//Creamos un vector donde almacenaremos las columnas que vamos a utilizar con el nombre de features
val assembler = new VectorAssembler().setInputCols(Array("age","balance","day","duration","campaign","pdays","previous")).setOutputCol("features")

//Aplicamos indexer la columna Y para que los valores de si y no los tome como valores numericos y podamos trabajar con ellos
val labelIndexer = new StringIndexer().setInputCol("y").setOutputCol("label")

//utilizamos randomSplit para crear datos de trabajo y test divididos en 70 y 30
val Array(training, test) = data.randomSplit(Array(0.7, 0.3), seed = 11L)

//Utilizamos linearSVM con los reatures y el Label del dataset
val lsvc = new LinearSVC().setLabelCol("label").setFeaturesCol("features").setPredictionCol("prediction").setMaxIter(10).setRegParam(0.1)

//se crea un nuevo pipeline
val pipeline = new Pipeline().setStages(Array(labelIndexer, assembler, lsvc))

// ajustamos el modelo
val model = pipeline.fit(training)

// tome los resultados en el conjunto test con transform
val result = model.transform(test)

// resultados en el conjunto Text 
val predictionAndLabelsrdd = result.select($"prediction", $"label").as[(Double, Double)].rdd

//inicialice un objeto multiclassMetrics
val metrics = new MulticlassMetrics(predictionAndLabelsrdd)

println("-------------- SVM ----------------")

// imprime la precision del algoritmo
println(s"Accuaracy Test = ${(metrics.accuracy)}")

//Tiempo de ejecucion
val t1 = System.nanoTime
val duration = (System.nanoTime - t1) / 1e9d
println("Tiempo de ejecucion: " + duration)

//Memoria usada
val mb = 1024*1024
val runtime = Runtime.getRuntime
println("** Used Memory:  " + (runtime.totalMemory - runtime.freeMemory) / mb)
println("** Free Memory:  " + runtime.freeMemory / mb)
println("** Total Memory: " + runtime.totalMemory / mb)
println("** Max Memory:   " + runtime.maxMemory / mb)

}
svm()



////////////////////////////////////        D E C I S I O N   T H R E E        ////////////////////////////////////////

def DT():Unit={
//Cargamos librerias a utilizar
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.IndexToString
import org.apache.log4j._
import org.apache.spark.ml.PipelineStage
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SQLContext
import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.ml.feature.VectorAssembler

//Reducción de errores
Logger.getLogger("org").setLevel(Level.ERROR)

//Creamos nuestra sesion de spark
val spark = SparkSession.builder().getOrCreate()

//Importamos nuestro DataSet
val data = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")

//Creamos nuestro label indexer para comparar 
val labelIndexer = new StringIndexer().setInputCol("y").setOutputCol("indexedLabel").fit(data)

//Inicializamos el vector asembler por datos de tipo numericos y agregamos la columna features como output 
val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","pdays","previous")).setOutputCol("features")
val features = assembler.transform(data)

// Identifica categoricamente nuestro dataset en vector 
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(features)

//Dividimos nuestro dataset en 70% entrenamiento y 30% de prueba
val Array(trainingData, testData) = features.randomSplit(Array(0.7, 0.3))

//Creamos un objeto DecisionTree
val dt = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")

//Rama de prediccion
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)

//Juntamos los datos en un pipeline
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))

//creamos modelo de entrenamiento
val model = pipeline.fit(trainingData)

//Transformacion de datos en el modelo
val predictions = model.transform(testData)

//Desplegamos predicciones
predictions.select("predictedLabel", "y", "features").show(5)

println("-------------- Decision Three ----------------")

//se genera el modelo de arbol
val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
println(s"Learned classification tree model:\n ${treeModel.toDebugString}")

//Evaluamos la eficiencia del modelo
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
println(s"Accuaracy Test = ${(accuracy)}")

//Tiempo de ejecucion
val t1 = System.nanoTime
val duration = (System.nanoTime - t1) / 1e9d
println("Tiempo de ejecucion: " + duration)

//Memoria usada
val mb = 1024*1024
val runtime = Runtime.getRuntime
println("** Used Memory:  " + (runtime.totalMemory - runtime.freeMemory) / mb)
println("** Free Memory:  " + runtime.freeMemory / mb)
println("** Total Memory: " + runtime.totalMemory / mb)
println("** Max Memory:   " + runtime.maxMemory / mb)
}
DT()




//////////////////////////////////         L O G I S T I C    R E G R E S I O N     ////////////////////////////////////////


def LR():Unit={
// Cargamos librerias
import org.apache.spark.sql.SparkSession
import org.apache.log4j._
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer, VectorAssembler}
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.ml.Pipeline
    
// Disminución de errores
Logger.getLogger("org").setLevel(Level.ERROR)
    
// Creamos sesion de spark
val spark = SparkSession.builder().getOrCreate()
    
// Cargamos nuestro dataset
val df = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")
 
//Creamos un vector donde colocaremos las columnas que queremos utilizar
val assembler = new VectorAssembler().setInputCols(Array("age","balance","day","duration","campaign","pdays","previous")).setOutputCol("features")
    
//Indexamos la columna Y para que los valores de si y no tomen valor de 1 y 0
val labelIndexer = new StringIndexer().setInputCol("y").setOutputCol("label")
val dataIndexed = labelIndexer.fit(df).transform(df)
    
// Dividimos los datos en 70% y 30% para el entrenamiento y prueba
val Array(training, test) = dataIndexed.randomSplit(Array(0.7, 0.3), seed = 12345)

// creamos nuestro logistic regresion
val lr = new LogisticRegression()

// creamos nuestro pipeline
val pipeline = new Pipeline().setStages(Array(assembler,lr))

// creamos nuestro modelo ingresandole el 70% de los datos
val model = pipeline.fit(training)

// generamos los resultados
val results = model.transform(test)

// Generamos predicciones
val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd

//creamos nuestro objeto evaluador para las multiclases de prediction and labels
val metrics = new MulticlassMetrics(predictionAndLabels)

println("-------------- Logistic Regresion ----------------")

//Predice las clases que hay en las columnas
println(metrics.confusionMatrix)

//Muestra la eficacia del modelo
println(s"accuaracy Test = ${(metrics.accuracy)}")
println(s"Test Error = ${(1.0 - metrics.accuracy)}")

//Tiempo de ejecucion
val t1 = System.nanoTime
val duration = (System.nanoTime - t1) / 1e9d
println("Tiempo de ejecucion: " + duration)

//Memoria usada
val mb = 1024*1024
val runtime = Runtime.getRuntime
println("** Used Memory:  " + (runtime.totalMemory - runtime.freeMemory) / mb)
println("** Free Memory:  " + runtime.freeMemory / mb)
println("** Total Memory: " + runtime.totalMemory / mb)
println("** Max Memory:   " + runtime.maxMemory / mb)
}
LR()



////////////////////////////////////        M U L T I L A Y E R    P E R C E P T R O N       ////////////////////////////////////////



def MLP():Unit={
//Cargamos las librerias que utilizaremos
import org.apache.spark.sql.SparkSession
import org.apache.log4j._
import org.apache.spark.ml.feature.IndexToString
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.linalg.Vectors
    
//Disminuye errores
Logger.getLogger("org").setLevel(Level.ERROR)
    
//Creamos sesion de spark
val spark = SparkSession.builder().getOrCreate()
    
//Cargamos nuestro dataset
val df = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank-full.csv")

//Creamos un vector donde seleccionaremos las columnas a utilizar de nuestro dataset
val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","pdays","previous")).setOutputCol("features")
val features = assembler.transform(df)
  
//Indexamos la columna "y" para poder utilizar los si y no como 0 y 1   
val labelIndexer = new StringIndexer().setInputCol("y").setOutputCol("label")
val dataIndexed = labelIndexer.fit(features).transform(features)
  
//Dividimos nuestro dataset en porciones de 70% y 30% para el entrenamiento y prueba de nuestro modelo
val split = dataIndexed.randomSplit(Array(0.7, 0.3), seed = 1234L)

//Asignamos 70% del total al entrenamiento
val train = split(0)

//Asignamos el 30% del total a las pruebas
val test = split(1)

//Asignamos el valor a las capas de nuestro modelo
val layers = Array[Int](5, 2, 3, 2)

//Creamos un entrenador para nuestro modelo con sus parametros
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

//Entrenamos nuestro modelo con el entrenamiento creado arriba
val model = trainer.fit(train)

//Proyecta resultados de nuestro modelo
val result = model.transform(test)
    
// creamos predicciones con columnas prediction y label
val predictionAndLabels = result.select("prediction", "label")
//visualizamos
predictionAndLabels.show(10)

println("-------------- Multilayer perceptron ----------------")

//muestra la eficiencia del modelo
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
println(s"Accuracy test = ${evaluator.evaluate(predictionAndLabels)}")

//Tiempo de ejecucion
val t1 = System.nanoTime
val duration = (System.nanoTime - t1) / 1e9d
println("Tiempo de ejecucion: " + duration)

//Memoria usada
val mb = 1024*1024
val runtime = Runtime.getRuntime
println("** Used Memory:  " + (runtime.totalMemory - runtime.freeMemory) / mb)
println("** Free Memory:  " + runtime.freeMemory / mb)
println("** Total Memory: " + runtime.totalMemory / mb)
println("** Max Memory:   " + runtime.maxMemory / mb)
}
MLP()