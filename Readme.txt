files included:
Eclipse project - car configuration
	packages - adapter, driver, exception, scale etc.
	error recording file - log.txt 
test_output_not_syn.txt:
	the result shows multiple threads editing an Option’s name without synchronization. As shown, the code execution of different threads are interleaved in time, which could corrupt the data if one thread’s computation depends on the outcome of the other.
test_output_sync.txt:
	the result shows multiple threads editing an Option’s name with synchronization. The lock make sure that when a thread is editing an Auto object, other threads cannot manipulate the same object until the owner thread finish and release the lock.
class diagram - CarConfig.pdf
I didn’t include Exception classes in the diagram since there are not enough space, and it is more clear to illustrate the newly added classes and their relationships with other classes.

Justify my design:
	I create two Thread classes each associated with single job, because I think this way is easier to manage thread operation, since each thread only associates with one run method.
	All synchronization is done inside Thread classes by locking on an Auto object. This way, threads want to manipulate the same Auto object has to wait for the other thread to release the resource. By isolating the synchronization in a separate multi-threading class and keep the original non-multithreading class intact, I think it would be easier to understand the synchronized behavior and maintain. It also minimize the usage of synchronization to reduce overhead. 
	I didn’t implement synchronization in Automobile’s methods, because I think it’s not necessary since all multi-threading operations are done through the EditSync API and EditOption classes.