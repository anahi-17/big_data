## Big Data

### The goal of k-means is simple: it groups similar data points in order to discover underlying patterns. To achieve this goal, k-means searches for a fixed number (k) of groupings (clusters) in the dataset.

### Evaluation 3

``
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.StringIndexer
import org.apache.log4j._
import org.apache.spark.ml.clustering.KMeans

// import org.apache.spark.ml.clustering.KMeansModel
import org.apache.spark.ml.linalg.Vectors

// we import session in spark
val spark = SparkSession.builder (). getOrCreate ()

// minimize errors
Logger.getLogger ("org"). SetLevel (Level.ERROR)

// Load our dataset
val data = spark.read.format ("csv"). option ("inferSchema", "true"). option ("header", "true"). csv ("Wholesale-customers-data.csv")

// we make a printschema to see the structure of the data
data.printSchema ()

// we select the columns required for the development of the problem
val feature_data = data.select ("Fresh", "Milk", "Grocery", "Frozen", "Detergents_Paper", "Delicassen")

// We generate a vector where the characteristics of the dataset to evaluate will be stored
// and is mG using the features column
val assembler = new VectorAssembler (). setInputCols (Array ("Fresh", "Milk", "Grocery", "Frozen", "Detergents_Paper", "Delicassen")). setOutputCol ("features")

// We transform the data using our dataset
val featureSet = assembler.transform (feature_data)


// We train our kmeans model with k = 3.
val kmeans = new KMeans (). setK (3) .setSeed (1L)
val model = kmeans.fit (featureSet)

// We evaluate the cluster with Within Set Sum of Squared Errors (WSSSE).
val WSSSE = model.computeCost (featureSet)
println (s "Within Set Sum of Squared Errors = $ WSSSE")

// Show the results
println ("Cluster Centers:")
model.clusterCenters.foreach (println)
``
### Collaborators
* **anahi-17** - [Github](https://github.com/anahi-17)
* **fernando-123** - [Github](https://github.com/fernando-123)
