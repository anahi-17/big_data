# datos_masivos
## Homework 1
### Types of machine learning algorithms
1. Regression algorithms. They help us classify or predict values. An attempt will be made to compensate for the best
response from the smallest error.
2. Bayesian algorithms
These types of algorithms by classification are included in Bayes' theorem and
they classify each value as independent of any other. What allows to predict
a class or category based on a given set of characteristics, using
probability
3. Grouping algorithms
They are used in unsupervised learning, and are used to categorize data that is not
tagged, that is, data without specific categories or groups.
The algorithm works by searching for groups within the data, with the
number of groups represented by variable K.
4. Decision tree algorithms
A decision tree is a tree structure similar to a flowchart that
uses a branching method to illustrate every possible outcome of a decision.
Each node within the tree represents a test on a specific variable, and
each branch is the result of that test.
5. Neural network algorithms.
Neural Networks mimic biological activation behavior and
interconnection between neurons to find non-linear solutions to problems
complex.
6. Dimension reduction algorithms
Dimension reduction reduces the number of variables considered for
find the exact information required.
Dimension reduction allows us to graph or simplify very complex models
that in their initial condition contained too many characteristics.
7. Deep Learning Algorithms
Deep learning algorithms execute data across multiple layers of
neural network algorithms, which pass to a simplified representation
from the data to the next layer.
Convolutional networks make a deep learning neural network
have the ability to recognize animals, humans and objects within images.

## Homework 3
### Pipeline
Pipeline architecture (based on filters) consists of transforming a data flow into a process comprised of several sequential phases, the input of each being the output of the previous one.
This architecture is very common in the development of programs for the command interpreter, since you can easily connect commands with pipes (pipe).
The pipe syntax, pipeline, allows connections to be clearly arranged in a sequence of multiple operations. It is a string syntax, so the operator% & gt; % takes the output ('the output') of a code statement and the conversion into the input ('the argument') of a new statement.

When describing the code we can think of it as "THEN".

The output (result) of the code to the left of%>% is argument of the function to the right.

### Confusion matrix
The confusion matrix of a problem of class n is an nxn matrix in which the rows are named according to the real classes and the columns, according to the classes predicted by the model. It is used to explicitly show when one class is confused with another. Therefore, it allows you to work separately with different types
of mistake.
For example, in a binary model that seeks to predict whether a mushroom is poisonous or not, based on certain physical characteristics of these we will consider the real classes p (positive = the mushroom is poisonous) and n (negative = the mushroom is edible), and
the classes predicted by the model, S (yes, it is poisonous), or N (no, it is edible).
In this way, the confusion matrix for this model has its rows labeled with the real classes, and its columns with those predicted by the model. It would look like this:

Thus, the main diagonal contains the sum of all the predictions
correct (the model says "S" and hits, is poisonous, or says "N" and hits also, is
edible). The other diagonal reflects the classifier errors: false positives or
"True positives" (says that it is poisonous "S", but in reality it is not "n"), or false negatives or "false negatives" (says that it is edible "N", but in fact it is poisonous "p" ).
