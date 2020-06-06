//Importamos las bibliotecas y paquetes necesarios para cargar el programa.
import  org.apache.spark.ml.classification. { LogisticRegression ,  OneVsRest } 
import  org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.sql.SparkSession

//Creamos una instancia de la sesión de spark
val spark = SparkSession.builder.appName("OneVsRestExample").getOrCreate()
//Carga de nuestro dataset 
val Data = spark.read.format("libsvm").load("sample_multiclass_classification_data.txt")
Data.show()
//Posteriormente realizamos nuestro entrenamiento con nuestros datos de la siguiente manera:
//divida los datos en conjuntos de entrenamiento y prueba por medio de un arreglo (20% para pruebas y 80% de entrenamiento).
val Array(train, test) = inputData.randomSplit(Array(0.8, 0.2))

//Instanciamos la base del clasificador el cual contendrá la máxima de interacciones la tolerancia y el ajuste de intercepciones.
val classifier = new LogisticRegression().setMaxIter(10).setTol(1E-6).setFitIntercept(true)
//Generamos las instancias de OneVsRest el cual nos va traer el clasificador
val ovr = new OneVsRest().setClassifier(classifier)
//Entrenamiento del modelo multiclases generando un ajuste a los datos de entrenamiento
val ovrModel = ovr.fit(train)

//Transformamos los datos de prueba en el metodo de prediccion
val predictions = ovrModel.transform(test)
//generamos el evaluador el cual traeremos la instancia nombre de la metrica
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

//calcula el error de clasificación en los datos de prueba.
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${1 - accuracy}")
