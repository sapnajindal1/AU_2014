Group- Neha Sharma, Sapna Jindal, Anithaprabha


We have used 4 design patterns-
	*simple factory
	*singleton
	*observer pattern
	*dependency injection

---------------------------------------------------------------------------------------------------------------------------


Simple Factory-

It is used in defining soldiers. 
There are 3 different kinds of soldiers- ground, air and water.
So we had an interface with functions that are common to all differnt types of soldiers.

---------------------------------------------------------------------------------------------------------------------------


Singleton-

Singleton is used for basecamp class. 
The constructor was declared private. 
Get instance method was written which was returns an instance of base camp object.
Singleton pattern was used because only a single object to basecamp should exist.

---------------------------------------------------------------------------------------------------------------------------


Observer Pattern-

Basecamp communicates with all the soldiers in the game. 
So use of observer pattern is advantageous here because all the registered observers (observer in this case are soldiers), can be notified by the subject (subject is the base camp) to fight in the battle or to return to the base.
The transport system can also be notified along with the soldiers to either deploy or pull back the soldiers from battle ground.

---------------------------------------------------------------------------------------------------------------------------


Dependency Injection-

Whenever there is a dependency in another class, we injected dependency using constructor.


---------------------------------------------------------------------------------------------------------------------------

