# Annotation Processor

##### Lombok 은 Ant 을 사용하는가?

https://github.com/projectlombok/lombok/wiki/LOMBOK-CONCEPT:-Why-does-lombok-build-with-ant

lombok은 ECJ 와 Javac Compiler 를 위한 Handler 를 제공한다. Build 도 맞쳐서 한다. 그만큼 Build 는 복잡해진다.

**이미 ant 로 복잡한 Build 를 돌리고 있기 때문에 maven, gradle 로 전환했을 경우, 시간적인 비용이 많이 들기 때문에 하지 않을 것이다.**



?? 어떻게 롬복에서는 이게 왜 되는거지? 아 processor 에 대한 정보를 가지고 있구나.

**Annotation Processor**

```java
public class MyProcessor extends AbstractProcessor {

	@Override
	public synchronized void init(ProcessingEnvironment env){ }

	@Override
	public boolean process(Set<? extends TypeElement> annoations, RoundEnvironment env) { }

	@Override
	public Set<String> getSupportedAnnotationTypes() { }

	@Override
	public SourceVersion getSupportedSourceVersion() { }

}
```

- `init(ProcessingEnvironment env)`: Every annotation processor class **must have an empty constructor**. However, there is a special `init()` method which is invoked by the annotation processing tool with the `ProcessingEnviroment` as parameter. The `ProcessingEnviroment` provides some useful util classes `Elements`, `Types` and `Filer`. We will use them later.
- `process(Set<? extends TypeElement> annotations, RoundEnvironment env)`: This is kind of `main()` method of each processor. Here you write your code for scanning, evaluating and processing annotations and generating java files. With `RoundEnviroment` passed as parameter you can query for elements annotated with a certain annotation as we will see later.
- `getSupportedAnnotationTypes()`: Here you have to specify for which annotations this annotation processor should be registered for. Note that the return type is a set of strings containing full qualified names for your annotation types you want to process with this annotation processor. In other words, you define here for which annotations you register your annotation processor.
- `getSupportedSourceVersion()`: Used to specify which java version you use. Usually you will return `SourceVersion.latestSupported()`. However, you could also return `SourceVersion.RELEASE_6` if you have good reasons for stick with Java 6. I recommend to use `SourceVersion.latestSupported();`







안되는 이유?

JAVA 9 부터 안되는 이유?

**JDK rt.jar and tools.jar 가 삭제됨** lombok에서도 1.6 에 있는 javax.annotation.processing.* 를 사용한다.

https://stackoverflow.com/questions/8375423/missing-artifact-com-suntoolsjar





## **문제**

Lombok 은 왜 SCL 바이너리 형태로 변경하였는가?

https://www.baeldung.com/lombok-custom-annotation

In fact, **the newer versions of Lombok use Shadow ClassLoader (SCL) to hide the \*.class\* files in Lombok as \*.scl\* files. Thus, it forces the developers to fork the Lombok source code and implement annotations there.**

On the positive side, **it simplifies the process of extending custom handlers and AST modification using utility functions.**



**그래서 주의할 점**

**An important note: Lombok 1.14.8 is the latest compatible version we can use to follow this tutorial. Since version 1.16.0, Lombok has hidden its internal API, and it's no longer possible to create custom annotations in the way we present here.**





Round ??

![img](https://raw.githubusercontent.com/LenKIM/images/master/2023-06-25/img.png)

## Processing Rounds



[참고자료]

annotationprocessing101 - http://hannesdorfmann.com/annotation-processing/annotationprocessing101/



[Annotation과 동작 원리](https://developer-youn.tistory.com/122)



