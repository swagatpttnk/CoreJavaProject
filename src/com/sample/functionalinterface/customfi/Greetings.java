package com.sample.functionalinterface.customfi;

public class Greetings {
    public String getGreetingsMsg(String msg,DisplayMsg d){
        return d.display(msg);
    }

    public static  void main(String[] args){
        Greetings gr=new Greetings();
        String ss=gr.getGreetingsMsg("Swagat", (s) ->  "Hello " + s);
          System.out.println("Greetings:"+ss);
        ss=gr.getGreetingsMsg("Swagat", (s) -> { return "Hi " + s; });
        System.out.println("Greetings:"+ss);
        ss=gr.getGreetingsMsg("Swagat", Greetings::customGreeting);
        System.out.println("Greetings:"+ss);
        ss=gr.getGreetingsMsg("Swagat", gr::hollaGreeting);
        System.out.println("Greetings:"+ss);
    }
    static String customGreeting(String s){ // method to be used as method ref should be static
        return "Bonjour "+s;
    }
    private String hollaGreeting(String s){
        return "Holla "+s;
    }
}
