package com.sefa.viewproducer.command;

import com.sefa.viewproducer.util.JsonFileReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;

@Slf4j
@ShellComponent
@RequiredArgsConstructor
public class SSHViewCommand {

    private static final String FILE_JSON = "json";

    private final JsonFileReader jsonFileReader;

    @ShellMethod(value = "read json view file", key = "read")
    public void readView(@NotBlank String path) {

        if (FILE_JSON.equals(FilenameUtils.getExtension(path))) {
            jsonFileReader.read(path);
        } else {
            log.warn("File should be .json");
        }
    }
}
