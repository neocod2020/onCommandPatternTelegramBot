package com.mycompany.onCommandPatternTelegramBot.command.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 *  Mark if {@ Command} can be viewed by admins only.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminCommand {
    
}
