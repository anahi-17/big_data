# datos_masivos
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

