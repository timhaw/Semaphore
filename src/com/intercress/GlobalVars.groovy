#!/usr/bin/env groovy
package com.intercress

class GlobalVars {
   static String foo = "bar"

   // refer to this in a pipeline using:
   //
   // import com.intercress.GlobalVars
   // println GlobalVars.foo
}
