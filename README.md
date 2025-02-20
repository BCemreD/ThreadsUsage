# ThreadsUsage
* An order monitoring simulation with concepts of thread creation, synchronization, deadlocks, and concurrent collections
* Business methods are in business.abstract package.
* Here I...Service interfaces are set in case of adding real db in the future.
* Business codes are in business. concretes package
* OrderManager class to rule order monitoring.
* OrderProcessor class to prevent thread problems
* CustomerManager and ProductManager classes are built to manage adding, removing, and displaying. Also, CustomerManager has a method that shows order history by customer's id.
* The project is meant to show thread usage basically, so some important functions are absent (such as entering mail address and phone rules, some methods).
* On-demand recalling all methods, main switch...case loop can be organized as a nested loop (Ex: main cases can be customer, product, order, exit and the other methods can be inner cases).
