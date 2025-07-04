@Aspect - annotation to define an Aspect
@Pointcut - pointcut designators
  - execution
  - this
  - @within
  - @target
  - @args
  - @annotation

##Concepts:

- **Aspect**  - concern implemented as part of class/method - like logging, security check, transaction etc
- **Advice** - action taken by aspect at particular join point
    - before advice
    - after returning advice
    - after throwing advice
    - after advice
    - around advice
- **Joint Point** - Code point during execution of a program
- **Pointcut** - predicate that matches Joint Point expression

weaving is load-time (other compile time and runtime) - uses java proxy or cglib
    
More details at project [AspectJSpringAOP](https://github.com/thanujkumar/AspectJSpringAOP)

