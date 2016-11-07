/*
 Copyright IBM Corporation 2016
 
 LICENSE: Apache License
          Version 2.0, January 2004
          http://www.apache.org/licenses/
          
 The following code is sample code created by IBM Corporation.
 This sample code is not part of any standard IBM product and
 is provided to you solely for the purpose of assisting you in
 the development of your applications.  The code is provided
 'as is', without warranty or condition of any kind.  IBM shall
 not be liable for any damages arising out of your use of the
 sample code, even if IBM has been advised of the possibility
 of such damages.
*/
package com.ibm.zosconnect.sample.ant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Parameter;

import com.ibm.zosconnect.buildtoolkit.InvalidPropertyException;
import com.ibm.zosconnect.buildtoolkit.SARGenerator;
import com.ibm.zosconnect.buildtoolkit.ServiceArtifactException;

public class Sar extends Task {
    
    private String sarFile;
    private ArrayList<Parameter> properties = new ArrayList<>();
    
    /**
     * Creates a configuration parameter that specifies the name
     * of the SAR file to create.
     * 
     * @param file The name of the file as a String
     */
    public void setFile(String file){
        this.sarFile = file;
    }
    
    /**
     * Adds the parameter to list of parameters passed to the build
     * toolkit.
     * 
     * This is configured in the build file as a nest element
     * <pre>
     * <property name="name" value="example" />
     * </pre>
     * 
     * @param param A parameter to be passed to the build toolkit
     */
    public void addConfiguredProperty(Parameter param){
        this.properties.add(param);
    }
    
    /* (non-Javadoc)
     * @see org.apache.tools.ant.Task#execute()
     */
    public void execute() throws BuildException {
        //Create a map of the configuration properties as required by
        //the build toolkit SPI
        Map<String, String> sarProperties = new HashMap<>();
        for(Parameter param : properties){
            sarProperties.put(param.getName(), param.getValue());
        }
        try{
            //Create a SARGenerator object, and pass the configuration
            SARGenerator generator = new SARGenerator(sarProperties);
            //Save the file to the specified location
            generator.save(sarFile);
        } catch (InvalidPropertyException | IOException | ServiceArtifactException e){
            //Expose any problems detected by the build toolkit to the Ant runtime
            throw new BuildException(e);
        }
    }

}
