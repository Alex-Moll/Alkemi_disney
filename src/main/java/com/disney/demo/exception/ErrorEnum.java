package com.disney.demo.exception;

public enum ErrorEnum {

    IDMOVIENOTVALID("Id Movie Not Valid"),
    IDCHARACTERNOTVALID("Id Character Not Valid"),
    IDGENERONOTVALID("Id Genero Not Valid"),
    USERALREADYEXIST("username Already Exist"),
    USERORPASSWORDNOTFOUND(" Username or Password not Found"),
    TRYINGTOSENDMAILFAIL("Error trying to send the mail"),
    MALFORMEDJASON("Entered data type error"),
    PARAMNOTFOUND("Param Not Found");
    
    private String message;
    
    ErrorEnum(String message){
        this.message=message;
    }
    
    public String getMessage(){
        return this.message;
    }
    
}
