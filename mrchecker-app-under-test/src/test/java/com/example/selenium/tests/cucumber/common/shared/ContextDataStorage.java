// package com.example.selenium.tests.common.shared;
//
// import com.example.core.exceptions.BFInputDataException;
// import org.springframework.stereotype.Component;
//
// import java.util.HashMap;
// import java.util.Map;
//
/// **
// * Created by LKURZAJ on 10.03.2017.
// */
// @Component
// public class ContextDataStorage {
// private Map<String,String> dataMap;
//
// ContextDataStorage(){
// dataMap = new HashMap<String, String>();
// }
//
// public void addStringValue(String key, String value){
// dataMap.put(key,value);
// }
//
// public String getStringValue(String key){
// if (!dataMap.containsKey(key)){
// throw new BFInputDataException( key + " object wasn't found in " + dataMap.keySet().toString());
// }
// return dataMap.get(key);
// }
// }
