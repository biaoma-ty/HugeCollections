Oversize Collections [LibOC]
============================
Oversize collections for JVMs. Store up to 9,223,372,036,854,775,807 objects in a collection, memory permitting.


Why?
----
In java, the default array size is restricted to a signed 32-bit value. Attempts to make arrays with 'long' sizes will not compile.
The java collections framework has this problem as well, on top of the fact that they are not a space-efficient set of classes. (They use up tons more RAM than an array would).
This library solves this problem by providing arbitrary length arrays. In future versions it will, if plans hold, contain replacements for many of the default collections data structures.


Information
-----------

License: LGPL.


Created by Ellen Hebert.
https://twitter.com/Cactose