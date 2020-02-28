# datos_masivos

Practice 1
In this practice basic operations are performed in Scala language.

	//1. Radio de un circulo
	val circunferencia: Double = 10 //The variable is declared, the type of variable is assigned and we give it a value.
	val radio: Double = circunferencia/(2*3.1416) //the variable is declared for the radius, the type of variable is assigned and 	
	the formula is made for the radius with the variable declared above
	println("El radio del circulo es: " + radio) //Print result

	//2. Numeros primos


	//3. Dada la variable bird = "tweet", utiliza interpolacion de string para imprimir "Estoy ecribiendo un tweet"
	var bird = "tweet" //We declare variable bird
	println(s"Estoy escribiendo un, $bird") //we interpolate the strings through the operator $ and print

	//4. Dada la variable mensaje = "Hola Luke yo soy tu padre!" utiliza slice para extraer la secuencia "Luke"
	var texto = "Hola Luke soy tu padre!" //we declare the text variable and match it to a sentence
	texto.slice(5,9)//con la funcion slice extraemos una palabra de la oracion

	//5. Cual es la diferencia en value y una variable en scala?
	//we declare the variable to be assigned a string
	val a = new String("A");
	a = "2";
	//with "var" objects that can be read and written
	var a = new String("A");
	a = "2";

	//6. Dada la tupla ((2,4,5),(1,2,3),(3.1416,23))) regresa el numero 3.1416
	val tupla=((2,4,5),(1,2,3),(3.1416,23)) //we declare the tuple and values
	println(tupla._3._1) //the value is extracted giving the coordinate to the tuple and printed


Practice 2
In this work we put into practice the creation, modification and extraction of elements of a list. In point 1 we create the list, in point 2 we build the list with more elements ::, in point 3 by means of the sliced function we extracted only a range of elements.
In point 4 an arrangement was created where by means of a “for” it shows us the numbers in vertical and another method that we use to create an arrangement with the indicated parameters, in this case it shows us the horizontal sequence.
In point 5 it shows us the unique elements of the list.
In point 6 the keys are assigned to our elements and in point 7 we add elements to the Map using + =.

	// 1. Crea una lista llamada "lista" con los elementos "rojo", "blanco", "negro"
	val lista = List("rojo","blanco","negro")
	 println(lista)	 
	// 2. Añadir 5 elementos mas a "lista" "verde" ,"amarillo", "azul", "naranja", "perla"
	lista.Añadir("verde" ,"amarillo", "azul", "naranja", "perla")
	val c1 = "Verde" :: lista
	val c2 = "Amarillo" :: c1
	val c3 = "Azul" :: c2
	val c4 = "Naranja" :: c3
	val c5 = "Perla" :: c4
	// 3. Traer los elementos de "lista" "verde", "amarillo", "azul"
	var lista = List("rojo", "blanco", "negro","verde","amarillo","azul", "naranja", "perla")
	lista.slice(3,6)
	// 4. Crea un arreglo de numero en rango del 1-1000 en pasos de 5 en 5
 	 Array.range(1, 1000, 5)

	val array = (1 to 1000).by(5)
	    for(i <- array)
	    {
	        println(""+i)
	    }
	// 5. Cuales son los elementos unicos de la lista Lista(1,3,3,4,6,7,3,7) utilice conversion a conjuntos
	val mylist = List(1,3,3,4,5,7,3,7)
	    mylist.toSet
	// 6. Crea una mapa mutable llamado nombres que contenga los siguiente
	//     "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"
	val nombres = collection.mutable.Map(("Jose",20), ("Luis",24), ("Ana",23), ("Susana",27))
	// 6 a . Imprime todas la llaves del mapa
	nombres.keys
	// 7 b . Agrega el siguiente valor al mapa("Miguel", 23)
	nombres += ("Miguel" -> 23)
	
	
Practice 4
In this practice, operations are performed on a dataframe to query data and display them, 
some of the operations are: describe, select, groupBy, withColumn among others.
	
	
	import org.apache.spark.sql.SparkSession

	val spark = SparkSession.builder().getOrCreate()

	val df = spark.read.option("header", "true").option("inferSchema","true")csv("Netflix_2011_2016.csv")

	df.printSchema()

	df.show()

	//1
	df.describe ("High").show //Describe the statistical values of the selected column
	//2
	df.select ("High","Close").show // Displays the related values of the consulted columns.
	//3 
	df.select ("Open","Low").filter("Close < 480").show // Display the related and selected columns and put a filter to only display 	those that are less than 480
	//4 
	df.groupBy ("Open").show
	//5
	df.first //   return the first column of the dataframe
	//6 
	df.columns // Returns the dataframe columns
	//7 
	val df2 = df.withColumn("HV Ratio", df("High")+df("Volume")) // Add a column that derives from the high and Volume column
	//8 
	df.select(min("Volume")).show() // Choose the volume column min
	//9 
	df.select(max("Volume")).show() // Opt the volume column max
	//10
	val df2 = df.withColumn("Year", year(df("Date"))) // Create the year column from the date column
	// 11 
	val df3 = df.withColumn("Month", month(df("Date"))) // Create the month column from the date column
	// 12 
	val df3 = df.withColumn("Day", dayofmonth(df("Date"))) // create the day column from the month and date column
	// 13
	al df3 = df.withColumn("Day", dayofyear(df("Date"))) // Create the day column from the year column
	// 14 
	df.select(corr($"High", $"Volume")).show() // returns the correlation between the High and Volume column
	// 15 
	df.select($"High").take(1) // Take 1 column from the column
	// 16 
	df.select("High").repartition().show() //Split the selected column
	// 17 
	df.sort($"High".asc).show() // Draw the High column
	// 18 
	df.select(avg("High")).show() // Show the high column average
	// 19 
	df.filter($"Close" < 480 && $"High" < 480).collectAsList() //create a list from a collection
	// 20
	df.select(last_day(df("Date"))).show() // return the last day of the date column

Unit 1 Exam
In the exam we are asked to perform the operation of the absolute value of a 3x3 square matrix by determining the difference in the diagonals of the matrix.

	val arr = (11,2,4,4,5,6,10,8,-12) //We declare a list with the established values

	val diagonal_1 =(arr._1)+(arr._5)+(arr._9) // We declare the variable of the first diagonal and obtain the positions of the 							      first diagonal by means of arr ._ (the_position)
	
	val diagonal_2 =(arr._3)+(arr._5)+(arr._7) // We declare the variable of the second diagonal and obtain the positions of the 							      second diagonal by means of "arr ._ (the_position)"


	// diagonalDifference
	def diagonalDifference(arr:List[Int], diagonal_1:Int, diagonal_2:Int): Int = {  //The function and its parameters are declared 												  to receive all integers
	
		val res = (diagonal_1-diagonal_2) // Declare variable to obtain the difference of diagonal 1 and 2.

		math.abs(res) // utilizamos la funcion del valor absoluto en Scala para obtener el valor positivo
	}

	diagonalDifference(List(11,2,4,4,5,6,10,8,-12), diagonal_1, diagonal_2) // We send values to respective parameters
	}

