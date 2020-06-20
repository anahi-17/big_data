import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.StringIndexer
import org.apache.log4j._
import org.apache.spark.ml.clustering.KMeans
//import org.apache.spark.ml.clustering.KMeansModel
import org.apache.spark.ml.linalg.Vectors


//importamos sesion en spark
val spark = SparkSession.builder().getOrCreate()

//minimizar errores
Logger.getLogger("org").setLevel(Level.ERROR)

//Cargamos nuestro dataset 
val data = spark.read.format("csv").option("inferSchema","true").option("header","true").csv("Wholesale-customers-data.csv")

//hacemos un printschema para ver la estructura de la data
data.printSchema()

//seleccionamos las columnas requeridas para el desarrollo del problema
val feature_data = data.select("Fresh","Milk","Grocery","Frozen","Detergents_Paper","Delicassen")

// Generamos un vector donde se almacenara las caracteristicas del dataset a evaluar 
// y se mG mediante la columna features   
val assembler = new VectorAssembler().setInputCols(Array("Fresh","Milk","Grocery","Frozen","Detergents_Paper","Delicassen")).setOutputCol("features")

// Transformamos los datos utlizando nuestro dataset 
val featureSet = assembler.transform(feature_data)


// Entrenamos nuestro modelo kmeans con k=3.
val kmeans = new KMeans().setK(3).setSeed(1L)
val model = kmeans.fit(featureSet)

// Evaluamos el cluster con Within Set Sum of Squared Errors(WSSSE).
val WSSSE = model.computeCost(featureSet)
println(s"Within Set Sum of Squared Errors = $WSSSE")

// Muestra los resultados
println("Cluster Centers: ")
model.clusterCenters.foreach(println)