package org.schors.vertx.telegram.bot.commands;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface BotCommand {
    String regexp() default "";

    boolean isDefault() default false;

    boolean isPostExecute() default false;
}
