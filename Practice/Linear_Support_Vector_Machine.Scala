// Impoortamos librerias
package org.apache.spark.examples.ml
//Libreria para SVC
import org.apache.spark.ml.classification.LinearSVC
import org.apache.spark.sql.SparkSession


    //Creacion de variable de session spark
    val spark = SparkSession.builder.appName("LinearSVCExample").getOrCreate()

    // cargamos los datos de nuestro archivo y lo agregamos a una variable para entrenar
    val training = spark.read.format("libsvm").load("/usr/local/spark-2.3.4-bin-hadoop2.6/data/mllib/sample_libsvm_data.txt")
    
    //creamos un objeto de tipo LinearSVC
    //establecemos el numero de iteraciones en 10 con el metodo setMaxIter
    //Establecer el parámetro de regularización
    val lsvc = new LinearSVC().setMaxIter(10).setRegParam(0.1)
    
    // Fit the model
    val lsvcModel = lsvc.fit(training)

    // Imprimimos los coeficientes del vector y su intercepcion
    println(s"Coefficients: ${lsvcModel.coefficients} Intercept: ${lsvcModel.intercept}")
