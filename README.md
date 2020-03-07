# Unit 1
Data mining is the set of techniques and technologies that allow you to explore large databases that allow you to find anomalies, patterns and correlations to predict results.

## Practice 1 
In this practice basic operations are performed in Scala language.

//1. Radius of a circle
val circumference: Double = 10 // The variable is declared, the type of variable is assigned and we give it a value.
val radius: Double = circumference / (2 * 3.1416) // the variable is declared for the radius, the type of variable is assigned and
the formula is made for the radius with the variable declared above
println ("The radius of the circle is:" + radius) // Print result

//2. Prime numbers

//3. Given the variable bird = "tweet", use string interpolation to print "I am writing a tweet"
var bird = "tweet" // We declare variable bird
println (s "I am writing a, $ bird") // we interpolate the strings through the operator $ and print

//4. Given the variable message = "Hi Luke, I'm your father!" use slice to extract the sequence "Luke"
var text = "Hi Luke, I'm your father!" // we declare the text variable and match it to a sentence
texto.slice (5,9) // with the slice function we extract a word from the sentence

//5. What is the difference in value and a variable in scala?
// we declare the variable to be assigned a string
val a = new String ("A");
a = "2";
// with "var" objects that can be read and written
var a = new String ("A");
a = "2";

// 6. Given the tuple ((2,4,5), (1,2,3), (3,114,23))) return the number 3.1416
val tupla = ((2,4,5), (1,2,3), (3,142,23)) // we declare the tuple and values
println (tupla._3._1) // the value is extracted giving the coordinate to the tuple and printed

## Practice 2 
In this work we put into practice the creation, modification and extraction of elements of a list. In point 1 we create the list, in point 2 we build the list with more elements ::, in point 3 by means of the sliced ​​function we extracted only a range of elements. In point 4 an arrangement was created where by means of a “for” it shows us the numbers in vertical and another method that we use to create an arrangement with the indicated parameters, in this case it shows us the horizontal sequence. In point 5 it shows us the unique elements of the list. In point 6 the keys are assigned to our elements and in point 7 we add elements to the Map using + =.

// 1. Create a list called "list" with the elements "red", "white", "black"
val list = List ("red", "white", "black")
 println (list)
 
// 2. Add 5 more elements to "list" "green", "yellow", "blue", "orange", "pearl"
list.Add ("green", "yellow", "blue", "orange", "pearl")
val c1 = "Green" :: list
val c2 = "Yellow" :: c1
val c3 = "Blue" :: c2
val c4 = "Orange" :: c3
val c5 = "Pearl" :: c4

// 3. Bring the "list" "green", "yellow", "blue" items
var list = List ("red", "white", "black", "green", "yellow", "blue", "orange", "pearl")
list.slice (3.6)

// 4. Create a number array in the 1-1000 range in 5-in-5 steps
 Array.range (1, 1000, 5)

val array = (1 to 1000) .by (5)
    for (i <- array)
    {
        println ("" + i)
    }
    
// 5. What are the unique elements of the List list (1,3,3,4,6,7,3,7) use conversion to sets
val mylist = List (1,3,3,4,5,7,3,7)
    mylist.toSet
    
// 6. Create a mutable map called names containing the following
// "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"
val names = collection.mutable.Map (("Jose", 20), ("Luis", 24), ("Ana", 23), ("Susana", 27))

// 6 a. Print all map keys
names.keys

// 7 . Add the following value to the map ("Miguel", 23)
names + = ("Miguel" -> 23)

## Practice 3 
In this practice we perform 5 different ways of performing the Fibonacci. The first was recursive which allows calling itself within its instructions "return fibonacci1 (n-1) + fibonacci1 (n-2)". The second form contains the mathematical object, which contains mathematical functions. The third form was implemented a "for" cycle starting with k = 1 and n will be the value entered. The fourth form will be used in 2 variables within the “for” cycle, whose result was the addition or subtraction of these each time the cycle was iterated. The fifth form created an array with a range. 
// Recursive Fiboniacci

val n = 5

def fibonacci1 (n: Int): Int = {if (n <2) {return n} else {return fibonacci1 (n-1) + fibonacci1 (n-2)}}

println (fibonacci1 (n))

// Algorithm 2 Version with explicit formula

val n = 4 var phi = ((1 + math.sqrt (5)) / 2) var j = ((math.pow (phi, n) -math.pow ((1-phi), n)) / ( math.sqrt (5)))

def fibonacci2 (n: Double): Double = {if (n <2) {return n} else {return j}} println (fibonacci2 (n))

// Algorithm 3 Iterative version

def fibonacci3 (n: Int): Int = {var n: Int = 6 var a = 0 var b = 1 var c = 0 var k = 0

for (k <- 1 to n)
{
    
    c = b + a
    a = b
    b = c
}
 return a
} println (fibonacci3 (n))

// Algorithm 4 Iterative version 2 variables (Complexity (O (n))

def fibonacci4 (n: Int): Int = {var n: Int = 10 var a = 0 var b = 1 var k = 0

for (k <- 1 to n) {
    b = b + a
    a = b - a

    }
 return a
} println (fibonacci4 (a))

// Algorithm 5 Iterative vector version

var n = 10 def fibonacci4 (n: Int): Int = {var array = Array (n + 2) var i: Int array (0,0) array (1,1)

for (i <- 1 to 2)
} println (fibonacci4 (a)) def fib (n: Int): Int = {val n = 10 val f: Array [Int] = Array.ofDim [Int] (n + 2) f (0) = 0 f ( 1) = 1

for (i <- 2 to n) {
  
  f (i) = f (i - 1) + f (i - 2)
}
f (n)
} println (fib (8))

## Practice 4 
In this practice, operations are performed on a dataframe to query data and display them, some of the operations are: describe, select, groupBy, withColumn among others.

import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder (). getOrCreate ()

val df = spark.read.option ("header", "true"). option ("inferSchema", "true") csv ("Netflix_2011_2016.csv")

df.printSchema ()

df.show ()

//1
df.describe ("High"). show // Describe the statistical values ​​of the selected column

//2
df.select ("High", "Close"). show // Displays the related values ​​of the consulted columns.

//3
df.select ("Open", "Low"). filter ("Close <480"). show // Display the related and selected columns and put a filter to only display those that are less than 480

//4
df.groupBy ("Open"). show

//5
df.first // return the first column of the dataframe

// 6
df.columns // Returns the dataframe columns

// 7
val df2 = df.withColumn ("HV Ratio", df ("High") + df ("Volume")) // Add a column that derives from the high and Volume column

// 8
df.select (min ("Volume")). show () // Choose the volume column min

// 9
df.select (max ("Volume")). show () // Opt the volume column max

// 10
val df2 = df.withColumn ("Year", year (df ("Date"))) // Create the year column from the date column

// 11
val df3 = df.withColumn ("Month", month (df ("Date"))) // Create the month column from the date column

// 12
val df3 = df.withColumn ("Day", dayofmonth (df ("Date"))) // create the day column from the month and date column

// 13
al df3 = df.withColumn ("Day", dayofyear (df ("Date"))) // Create the day column from the year column

// 14
df.select (corr ($ "High", $ "Volume")). show () // returns the correlation between the High and Volume column

// 15
df.select ($ "High"). take (1) // Take 1 column from the column

// 16
df.select ("High"). distribution (). show () // Split the selected column

// 17
df.sort ($ "High" .asc) .show () // Draw the High column

// 18
df.select (avg ("High")). show () // Show the high column average

// 19
df.filter ($ "Close" <480 && $ "High" <480) .collectAsList () // create a list from a collection

// 20
df.select (last_day (df ("Date"))). show () // return the last day of the date column

# Exam 1
In the exam we are asked to perform the operation of the absolute value of a 3x3 square matrix by determining the difference in the diagonals of the matrix.

val arr = (11,2,4,4,5,6,10,8, -12) // We declare a list with the established values

val diagonal_1 = (arr._1) + (arr._5) + (arr._9) // We declare the variable of the first diagonal and obtain the positions of the first diagonal by means of arr ._ (the_position)

val diagonal_2 = (arr._3) + (arr._5) + (arr._7) // We declare the variable of the second diagonal and obtain the positions of the second diagonal by means of "arr ._ (the_position)"


// diagonalDifference
def diagonalDifference (arr: List [Int], diagonal_1: Int, diagonal_2: Int): Int = {// The function and its parameters are declared to receive all integers

val res = (diagonal_1-diagonal_2) // Declare variable to obtain the difference of diagonal 1 and 2.

math.abs (res) // we use the absolute value function in Scala to obtain the positive value
}

diagonalDifference (List (11,2,4,4,5,6,10,8, -12), diagonal_1, diagonal_2) // We send values ​​to respective parameters
}

### Collaborators @github
*anahi-17

*fernando-123
