#!/usr/bin/env groovy
import java.io.*;

String scripts = new File(getClass().protectionDomain.codeSource.location.path).parent
String source = new File(scripts + '/src/main/resources/jsf/i18n/text_ru.properties').path
String n2ascii = System.properties.find {it.key == "java.home"}.value + '/../bin/native2ascii -encoding utf-8 '
String dst = new File(scripts + '/target/classes/jsf/i18n/text_ru.properties').path
sprintf('%s %s %s', n2ascii, source, dst).execute()