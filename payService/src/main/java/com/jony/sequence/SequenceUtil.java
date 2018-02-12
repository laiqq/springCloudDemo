package com.jony.sequence;

/**
 * jony.lai
 */

import org.springframework.beans.factory.annotation.Value;
import com.jony.sequence.Sequence;

public class SequenceUtil {
	
	@Value("${sequence.workerId}")
    private static long workerId;
	
	@Value("${sequence.dataBaseId}")
    private static long dataBaseId;
	
    public SequenceUtil() {
    	
    }
    private static Sequence sequence = null;
    
    public static Sequence getSequence() {
        if(sequence == null){
        	sequence = new Sequence(workerId,dataBaseId);
        }
        return sequence;
    }



}