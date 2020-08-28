# junit-xml2csv

A tool to convert the JUnit result XML file to CSV.

## Usage

Java (JDK8 or higher) is required for execution.

Download the latest jar file (`junit-xml2csv-x.x.x-all.jar`) from below.

* https://github.com/onozaty/junit-xml2csv/releases/latest

Execute the application with the following command.

```
java -jar junit-xml2csv-1.0.0-all.jar ./build/test-results/test ./junit-result.csv
```


## Examples

### Input JUnit result XML files

```xml
<?xml version="1.0" encoding="UTF-8"?>
<testsuite name="com.github.onozaty.junit.xml2csv.TestCase1" tests="5" skipped="1" failures="1" errors="1" timestamp="2020-08-28T04:39:49" hostname="DESKTOP-U275NQ4" time="0.003">
  <properties/>
  <testcase name="test1" classname="com.github.onozaty.junit.xml2csv.TestCase1" time="0.0"/>
  <testcase name="test2" classname="com.github.onozaty.junit.xml2csv.TestCase1" time="0.0">
    <failure message="java.lang.AssertionError: xxxxx" type="java.lang.AssertionError">java.lang.AssertionError: xxxx
	at org.junit.Assert.fail(Assert.java:88)
	at com.github.onozaty.junit.xml2csv.TestCase1.test2(TestCase1.java:16)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
    </failure>
  </testcase>
  <testcase name="test3" classname="com.github.onozaty.junit.xml2csv.TestCase1" time="0.001">
    <error message="java.lang.RuntimeException" type="java.lang.RuntimeException">java.lang.RuntimeException
	at com.github.onozaty.junit.xml2csv.TestCase1.test3(TestCase1.java:21)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    </error>
  </testcase>
  <testcase name="test4" classname="com.github.onozaty.junit.xml2csv.TestCase1" time="0.0">
    <skipped/>
  </testcase>
  <testcase name="test5" classname="com.github.onozaty.junit.xml2csv.TestCase1" time="0.002"/>
  <system-out><![CDATA[aaaaa
]]></system-out>
  <system-err><![CDATA[]]></system-err>
</testsuite>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<testsuite name="com.github.onozaty.junit.xml2csv.TestCase2" tests="2" skipped="0" failures="1" errors="0" timestamp="2020-08-28T04:39:50" hostname="DESKTOP-U275NQ4" time="0.002">
  <properties/>
  <testcase name="test1" classname="com.github.onozaty.junit.xml2csv.TestCase2" time="0.0"/>
  <testcase name="test2" classname="com.github.onozaty.junit.xml2csv.TestCase2" time="0.002">
    <failure message="java.lang.AssertionError: zzzz" type="java.lang.AssertionError">java.lang.AssertionError: zzzz
	at org.junit.Assert.fail(Assert.java:88)
	at com.github.onozaty.junit.xml2csv.TestCase2.test2(TestCase2.java:15)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
</failure>
  </testcase>
  <system-out><![CDATA[]]></system-out>
  <system-err><![CDATA[]]></system-err>
</testsuite>
```


### Output CSV file

```csv
TestSuite: Name,TestSuite: Timestamp,TestCase: ClassName,TestCase: Name,TestCase: Time,TestCase: Result
com.github.onozaty.junit.xml2csv.TestCase1,2020-08-28T04:39:49,com.github.onozaty.junit.xml2csv.TestCase1,test1,0.0,PASSED
com.github.onozaty.junit.xml2csv.TestCase1,2020-08-28T04:39:49,com.github.onozaty.junit.xml2csv.TestCase1,test2,0.0,FAILURE
com.github.onozaty.junit.xml2csv.TestCase1,2020-08-28T04:39:49,com.github.onozaty.junit.xml2csv.TestCase1,test3,0.001,ERROR
com.github.onozaty.junit.xml2csv.TestCase1,2020-08-28T04:39:49,com.github.onozaty.junit.xml2csv.TestCase1,test4,0.0,SKIPED
com.github.onozaty.junit.xml2csv.TestCase1,2020-08-28T04:39:49,com.github.onozaty.junit.xml2csv.TestCase1,test5,0.002,PASSED
com.github.onozaty.junit.xml2csv.TestCase2,2020-08-28T04:39:50,com.github.onozaty.junit.xml2csv.TestCase2,test1,0.0,PASSED
com.github.onozaty.junit.xml2csv.TestCase2,2020-08-28T04:39:50,com.github.onozaty.junit.xml2csv.TestCase2,test2,0.002,FAILURE
```
