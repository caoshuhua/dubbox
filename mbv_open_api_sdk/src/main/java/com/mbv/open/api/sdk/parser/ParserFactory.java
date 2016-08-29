package com.mbv.open.api.sdk.parser;

public class ParserFactory
{
  private static final Parser JSON_PARSER = new JsonParser();
  
  public static Parser getJsonParser()
  {
    return JSON_PARSER;
  }

}

