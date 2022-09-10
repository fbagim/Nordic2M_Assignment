# Nordic2M_Assignment

This is Provide encrypt /decrypt API Data service to API consumers. Tech Stak based on Java / Spring boot to receive  API request and provide response.

### Prior Requirement
•	Java 8 +

•	Maven

•	Postman / Any REST Client For Testing 

### Installation and run application
1. Pull repository for GitHub - https://github.com/fbagim/Nordic2M_Assignment.git

2. Execute Maven Build inside project
    > mvn clean install
    
3. To start API application please find jar file under build path - <project>/target/Nordic2M_Assignment-0.0.1-SNAPSHOT.jar
 
    >  To execute jar -  Java -jar  Nordic2M_Assignment-0.0.1-SNAPSHOT.jar
    
    API services will start on localhost with this url http://localhost:9090/cipherApi

 ### To Query API
 
 1. Start Postman
 
 2. Application secured with basic Auth since you need to set credentials on Postman to access API.
    > User Name -admin , Password – root
    
     ![image](https://user-images.githubusercontent.com/7611920/189490560-1860c8ee-880d-44a5-a454-9eba8628d211.png)

    To encrypt and expected String , its required to use  Api use below payload and send request on POST method to API end point.
    
    > Endpoint - http://localhost:9090/cipherApi/encrypt
    
    > Payload 
    
        {
            "requestData":  "abcdefghijklmnopqrstuvwxyz",
            "noOfRotations used for " : 3,
            "responseData": null
        }
     > Required Fields – requestData , noOfRotations
     
     > Response will be
     
        {
            "requestData": "abcdefghijklmnopqrstuvwxyz",
            "responseData": "defghijklmnopqrstuvwxyzabc",
            "noOfRotations": 3
        }
        
        ![image](https://user-images.githubusercontent.com/7611920/189490834-929e1787-b41f-467a-99dd-09ec7c013e68.png)

    To Decrypt any Encrypted String ,its required to use Api use below payload and send request on POST method to API end point with noOfRotations used for Encryption. 
   
    > Endpoint -http://localhost:9090/cipherApi/decrypt
    
    > Payload 

       {
            "requestData":  "defghijklmnopqrstuvwxyzabc",
            "noOfRotations" : 3,
            "responseData": null
        }

     > Required Fields – requestData , noOfRotations


     > Response will be
        {

          "requestData": "defghijklmnopqrstuvwxyzabc",
          "responseData": "abcdefghijklmnopqrstuvwxyz",
          "noOfRotations": 3

        }
        
   ![image](https://user-images.githubusercontent.com/7611920/189492402-ce9d6b8c-7e82-417c-b56d-bacc953e5496.png)

### Answers

-  What is the decrypted message of following string if Number of Rotations equals 5.
   > Xtrj-2R-"xtkybfwj jslnsjjwx"-bnqq gj otnsnsl ymj rnxxnts yt--Rfwx--!!!
   > This added to test case - CipherServiceTest.class -> testDecryptForValidStringWithRotationsEqualsTo5  
  
  Answer - "Some-2M-"software engineers"-will be joining the mission to--Mars--!!!"

### Time and space complexity

For the existing algorithm space time complexity – 
If one character to consume 1 "time unit", then algorithm will do 26*N translations. plus some (more or less) constant overhead C, so it's 26*N + C time units. In Big-O notation, we can ignore any constant factors and additions, and come to the final result of O(N).

Seems it having some challenges to remove existing one loop and If – Else conditions on algorithm  .

There is one improvement we cand add to this solution by adding cache  implementation in middle if we are applying this solution to large size strings for the processing to reduce over head of string find and replace in every time.

    > calculateCipher(String text,int shift) {
          String result = "";
          Map<Character,Character> cache = new HashMap<>();
          for (Character c : text.toCharArray() ){
           // check whether change character is available on Map /Cache and append to results 
              if(cache.get(c)) != null)  {
                  result += c;
              } else {	        
                  Charcter rc += c+shift ; 
                  cache.put(c,rc);
              }
          }
      }

     
    
     

  


     


  
