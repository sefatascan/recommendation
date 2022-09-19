package com.sefa.viewproducer.config;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.lang.String.format;

@Component
public class CustomPromptProvider implements PromptProvider {

    @Override
    public AttributedString getPrompt() {
        String applicationName = "View Producer App";
        String now = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String brace = "->";
        return new AttributedString(format("%s [%s]%n%s", applicationName, now, brace),
                                    AttributedStyle.BOLD.foreground(AttributedStyle.BLUE).blink());
    }
}
