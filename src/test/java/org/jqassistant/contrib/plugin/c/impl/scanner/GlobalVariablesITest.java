package org.jqassistant.contrib.plugin.c.impl.scanner;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.util.List;

import org.jqassistant.contrib.plugin.c.api.model.CAstFileDescriptor;
import org.jqassistant.contrib.plugin.c.api.model.CDescriptor;
import org.jqassistant.contrib.plugin.c.api.model.TranslationUnitDescriptor;
import org.jqassistant.contrib.plugin.c.api.model.VariableDescriptor;
import org.junit.Test;

import com.buschmais.jqassistant.core.scanner.api.DefaultScope;
import com.buschmais.jqassistant.core.scanner.api.Scanner;
import com.buschmais.jqassistant.core.store.api.model.Descriptor;
import com.buschmais.jqassistant.plugin.common.test.AbstractPluginIT;

public class GlobalVariablesITest extends AbstractPluginIT{

	@Test
	public void testSimpleGlobalVariables() {
		store.beginTransaction();
        
        File testFile = new File(getClassesDirectory(GlobalVariablesITest.class), "/global_variables_simple.ast");
        Scanner scanner = getScanner();
        CAstFileDescriptor fileDescriptor = store.create(CAstFileDescriptor.class);
        Descriptor returnedDescriptor = scanner.scan(testFile, fileDescriptor, testFile.getAbsolutePath(), DefaultScope.NONE);

        CAstFileDescriptor descriptor = (CAstFileDescriptor) returnedDescriptor;
        TranslationUnitDescriptor translationUnitDescriptor = descriptor.getTranslationUnit();
        List<VariableDescriptor> variableList = translationUnitDescriptor.getDeclaredVariables();
        
        for(CDescriptor cDescriptor : variableList) {
        	if(cDescriptor instanceof VariableDescriptor) {
        		VariableDescriptor variableDescriptor = (VariableDescriptor) cDescriptor;
        		assertEquals("global_variables_simple.c", variableDescriptor.getFileName());
        		switch (variableDescriptor.getName()) {
    			case "intExample":
    				assertEquals("int", variableDescriptor.getTypeSpecifiers().getName());
    				break;
    			case "charExample":
    				assertEquals("char", variableDescriptor.getTypeSpecifiers().getName());
    				break;
    			case "shortExample":
    				assertEquals("short", variableDescriptor.getTypeSpecifiers().getName());
    				break;
    			case "longExample":
    				assertEquals("long", variableDescriptor.getTypeSpecifiers().getName());
    				break;
    			case "unsignedExample":
    				assertEquals("unsigned", variableDescriptor.getTypeSpecifiers().getName());
    				break;
    			case "signedExample":
    				assertEquals("signed", variableDescriptor.getTypeSpecifiers().getName());
    				break;
    			case "floatExample":
    				assertEquals("float", variableDescriptor.getTypeSpecifiers().getName());
    				break;
    			case "doubleExample":
    				assertEquals("double", variableDescriptor.getTypeSpecifiers().getName());
    				break;
    			case "boolExample":
    				assertEquals("_Bool", variableDescriptor.getTypeSpecifiers().getName());
    				break;
    			default:
    				break;
    			}
        	}
        }
        
        store.commitTransaction();
	}
	
	@Test
	public void testComplexGlobalVariables() {
		store.beginTransaction();
        
        File testFile = new File(getClassesDirectory(GlobalVariablesITest.class), "/global_variables_complex.ast");
        Scanner scanner = getScanner();
        CAstFileDescriptor fileDescriptor = store.create(CAstFileDescriptor.class);
        Descriptor returnedDescriptor = scanner.scan(testFile, fileDescriptor, testFile.getAbsolutePath(), DefaultScope.NONE);

        CAstFileDescriptor descriptor = (CAstFileDescriptor) returnedDescriptor;
        TranslationUnitDescriptor translationUnitDescriptor = descriptor.getTranslationUnit();
        List<VariableDescriptor> variableList = translationUnitDescriptor.getDeclaredVariables();
        
        for(CDescriptor cDescriptor : variableList) {
        	if(cDescriptor instanceof VariableDescriptor) {
        		VariableDescriptor variableDescriptor = (VariableDescriptor) cDescriptor;
        		assertEquals("global_variables_complex.c", variableDescriptor.getFileName());
        		switch (variableDescriptor.getName()) {
        		case "i":
        			assertEquals("int", variableDescriptor.getTypeSpecifiers().getName());
        			break;
        		case "ptr":
        			assertEquals("int *", variableDescriptor.getTypeSpecifiers().getName());
        			break;
        		case "text":
        			assertEquals("char *", variableDescriptor.getTypeSpecifiers().getName());
        			break;
        		case "doubleArray":
        			assertEquals("double []", variableDescriptor.getTypeSpecifiers().getName());
        			break;
        		case "intArray":
        			assertEquals("int [5]", variableDescriptor.getTypeSpecifiers().getName());
        			break;
        		case "boolPtr":
        			assertEquals("_Bool *", variableDescriptor.getTypeSpecifiers().getName());
        			break;
        		default:
        			break;
        		}
        	}
        }
        
        store.commitTransaction();
	}
	
	@Test
	public void testTypeQualifiers() {
		store.beginTransaction();
        
        File testFile = new File(getClassesDirectory(FunctionITest.class), "type_qualifiers.ast");
        Scanner scanner = getScanner();
        CAstFileDescriptor fileDescriptor = store.create(CAstFileDescriptor.class);
        Descriptor returnedDescriptor = scanner.scan(testFile, fileDescriptor, testFile.getAbsolutePath(), DefaultScope.NONE);

        CAstFileDescriptor descriptor = (CAstFileDescriptor) returnedDescriptor;
        TranslationUnitDescriptor translationUnitDescriptor = descriptor.getTranslationUnit();
        List<VariableDescriptor> variableList = translationUnitDescriptor.getDeclaredVariables();
        for(CDescriptor cDescriptor : variableList) {
        	if(cDescriptor instanceof VariableDescriptor) {
        		VariableDescriptor variableDescriptor = (VariableDescriptor) cDescriptor;
            	switch (variableDescriptor.getName()) {
    			case "p_ci":
    				assertEquals("int const *", variableDescriptor.getTypeSpecifiers().getName());
    				break;
    			case "p_ci2":
    				assertEquals("int const *", variableDescriptor.getTypeSpecifiers().getName());
    				break;
    			case "cp_i":
    				assertEquals("int * const", variableDescriptor.getTypeSpecifiers().getName());
    				break;
    			case "cp_i2":
    				assertEquals("int * const", variableDescriptor.getTypeSpecifiers().getName());
    				break;
    			case "vint":
    				assertEquals("int volatile", variableDescriptor.getTypeSpecifiers().getName());
    				break;
    			default:
    				break;
    			}
        	}
        }
	
        store.commitTransaction();
	}
}
