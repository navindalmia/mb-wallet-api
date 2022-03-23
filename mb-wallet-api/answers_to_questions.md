1.How long did you spend on the coding test? What would you add to your solution if you spent more time on it? 
If you didn't spend much time on the coding test then use this as an opportunity to explain what you would add.

Ans. I spent approximately 20-25 hours. This includes time involved to understand basic nuances of Ratpack and Redis as I have never used them before.
I would have added further input validations; logging of requests for analysis and error resolution if spent more time.

2. What was the most useful feature that was added to Java 8? Please include a snippet of code that shows how you've used it.

Ans. According to me, most useful feature that was added to Java 8 is the Lambda expressions. For example, Ratpack implementation extensively uses it like below:
RatpackServer.start(serverDefinition -> serverDefinition.handlers)
or like 
userTransactionPromise.then(userTransactionList -> respondWithTransactions(ctx, userTransactionList))
3. What is your favourite framework / library / package that you love but couldn't use in the task? What do you like about it so much?

Ans. I would have loved to use Spring Boot, given that it has wide variety of starters. It is easy to use and widely used. 
Having said that, it was a wonderful opportunity to work on newer tech stack Ratpack/Jedis and now am very happy that it was not on Spring Boot.

4. What great new thing you learnt about in the past year and what are you looking forward to learn more about over the next year?

Ans. Until this assignment it was Spring Boot and had planned to learn further implementations using it. But now I am having a growing fondness on Ratpack and NoSql type of Database like Redis. I would learn nore on such non-blocking frameworks like Ratpack and NoSql database like Redis.

5. How would you track down a performance issue in production? Have you ever had to do this? Can you add anything to your implementation to help with this?

Ans. Performance issues can be due to various reasons. It might originate from Application Server or from I/O requests or database queries or network latencies and so on.
I have faced many such instances, when it was due to database SQL queries, I had optimised queries by reducing cost and analysing Execution Plans. 
For Application layer, I have analysed JMap and JStack to understand the bottlenecks. Sometimes we need to find why a particular transactions always takes more amount of time, for that we take a look at profiling tools like Fiddler and also check logs in debug mode to understand which particular method call or database call is consuming more time. 

With reference to above API we can add Zipkin for distributed tracing. It helps to trace and analyse the path a request takes till a response is returned.

6. How would you improve the APIs that you just used?

Ans. I would improve the performance by using separate Blocking thread pool rather than Request processing execute thread. If possible I would also provide multi-currency options.


7. Please describe yourself in JSON format.

Ans. Please find the JSON as below:
{
	"firstName": "Navin",
	"lastName": "Dalmia",
	"age": 36,
	"nationality": "Indian",
	"residence": "Isle of Man",
	"occupation": "Software Developer",
	"education": "Bachelor of Technology in Computer Science and Engineering",
	"aspiration": "to work on new technologies",
	"hobbies": [Swimming, Yoga]
}

8. What is the meaning of life?

Ans. Life is something which has no beginning or end, it transcends all boundaries, it is too enormous to be defined or explained. I believe life to me is what fraction of it I can really live, enjoy and gain experiences. The moment I stop doing what I love to or stop loving what I get out of this pursuit of life, it will end.

Thanks for bearing with me, hope to hear from you soon!
