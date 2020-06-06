// We import the libraries to use
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.StringIndexer

//1.- We load our dataset
val data = spark.read.format("csv").option("inferSchema","true").option("header","true").csv("iris.csv")
//Liempieza de datos
val data2 = data.na.drop()

//2.- What are the names of the columns?
//Muestra nombre de las columnas
data2.columns

//3.- How is the scheme?
//Muestra la estructura del dataframe
data2.printSchema()

//4.- Print the first 5 columns.
//Muestra las 5 primeras columnas
data2.show(5)

//5.- Use the describe () method to learn more about the DataFrame data.
// Give general information about dataframe data
data2.describe().show

//6.- Make the pertinent transformation for the categorical data which will be our labels to classify.
// We generate a vector where the characteristics of the dataset to be evaluated will be stored and saved using the new features column
val assembler = new VectorAssembler().setInputCols(Array("sepal_length","sepal_width","petal_length","petal_width")).setOutputCol("features")
// We transform the data using our dataset 
val featureSet = assembler.transform(data2)
// We show that the features column was added
featureSet.show()
// We transform the categorical values ​​to numerical data to be able to process it
val labelIndexer = new StringIndexer().setInputCol("species").setOutputCol("label")
val dataindex = labelIndexer.fit(featureSet).transform(featureSet)
dataindex.show()
// The data frame is divided into training data and test data 60% for training and 40% for testing
val splits = dataindex.randomSplit(Array(0.6, 0.4), seed = 1234L)
val train = splits(0)
val test = splits(1)

//7.- Build the classification models and explain its architecture.
// We establish the layers of our neural network where: 4 are input, 5 and 4 are intermediate or hidden layers, and 3 are output.
val layers = Array[Int](4, 5, 4, 3)
// We do the data training applying our multilayerPerceptron algorithm
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)
val model = trainer.fit(train)

// The model test function is carried out and saved in the variable result
val result = model.transform(test)

// Using the select method that will use the prediction and lable variables
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
predictionAndLabels.show()

//8.- We print the accuracy of the model prediction.
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
