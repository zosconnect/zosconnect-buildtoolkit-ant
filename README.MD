## z/OS Connect EE Built Toolkit Ant Tasks

Ant Tasks which expose the functionality of the z/OS Connect EE Build Tookit for inclusion in an Ant build file.

### Getting started

1. Get a copy of the [z/OS Connect EE Build Toolkit](http://www.ibm.com/support/knowledgecenter/SS4SVW_2.0.0/com.ibm.zosconnect.doc/installing/bt_install.html)
2. Clone this repository `git clone repo`
3. Edit the `build.xml` file to point at the location of the build toolkit.
4. Build the Tasks `ant build.xml`

### Using in a build file

 1. Define the task
 ```
 <taskdef name="sar" classname="com.ibm.zosconnect.sample.ant.Sar">
    <classpath id="btclasspath">
        <pathelement path="/path/to/com.ibm.zosconnect.sample.buildtoolkit.task.jar" />
        <fileset dir="/path/to/zconbt/lib">
            <include name="*.jar" />
        </fileset>
        <fileset dir="/path/to/zconbt/plugins">
            <include name="*.jar" />
        </fileset>
    </classpath>
</taskdef>
 ```
2. Build the SAR file
```
<target name="build">
    <sar file="example.sar">
        <property name="name" value="example" />
        <property name="version" value="1.0" />
        <property name="provider" value="rest" />
        <property name="requestSchemaFile" value="request.json" />
        <property name="responseSchemaFile" value="response.json" />
        <property name="uri" value="/example/item" />
        <property name="connectionRef" value="exampleConn" />
    </sar>
</target>
```

### Notice

&copy; Copyright IBM Corporation 2016

### License
```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```