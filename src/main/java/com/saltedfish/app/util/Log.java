package com.saltedfish.app.util;

import com.saltedfish.app.controller.file.DownLoadController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    private Log(){}

    private static final Logger logger = LoggerFactory.getLogger(DownLoadController.class);

    public final static void d(String tag,String message){
        logger.info(tag +" : "+ message);
    }
}
