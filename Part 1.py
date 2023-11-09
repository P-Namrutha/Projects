#I declare that my contains no examples of misconduct, such as plagarism or collusion
#Any code taken from other sources is referenced within my code solution.
#Student ID: 20220922
#Date:13/11/2022

#Part I -  A & B
progress = 0
trailer = 0
retriever = 0
exclude = 0

list_volume = [0,20,40,60,80,100,120]

#Using conditional statements to check whether the entered input is correct or not
def outcomes(pass_credit,defer_credit):
    global progress
    global trailer
    global retriever
    global exclude
    
    if pass_credit == 120:
        print("Progress")
        progress = progress +1
       
    elif pass_credit == 100:
        print("Progress (module trailer)")
        trailer = trailer +1
        
    elif pass_credit == 80:
        print("module retriever")
        retriever = retriever +1
       
    elif pass_credit == 60:
        print("module retriever")
        retriever = retriever +1
        
    elif pass_credit == 40 and defer_credit ==0:
        print("Exclude")
        exclude = exclude +1
        
    elif pass_credit == 40 and defer_credit != 0:
        print("module retriever")
        retriever = retriever +1
    
    elif pass_credit == 20 and defer_credit >=40:
        print("module retriever")
        retriever = retriever + 1
        
    elif pass_credit == 20 and defer_credit <= 20:
        print("Exclude")
        exclude = exclude +1
        
    elif pass_credit == 0 and defer_credit >= 60:
        print("module retriever")
        retriever = retriever +1
        
    elif pass_credit == 0 and defer_credit <= 40:
        print("Exclude")
        exclude = exclude +1

#Defining the histogram structure
def histogram(progress,trailer,retriever,exclude):  
    star = "*"
    print(" _ _ _  _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _")
    print("Histogram")
    print("Progress",progress,":",star * progress)
    print("Trailer",trailer,":",star * trailer)
    print("Retriever",retriever,":",star * retriever)
    print("Excluded",exclude,":",star * exclude)
    outcome = progress + trailer + retriever + exclude
    print("")
    print(outcome,"outcomes in total")
    print(" _ _ _  _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _")

        
while True:#Infinite loop
    def input_validation():
        while True:
            try:
                pass_credit = int(input("Please enter your credit at pass : "))
            except ValueError:
                print("Interger required")
                continue
            else:
                if pass_credit not in list_volume:
                    print("Out of range")
                elif pass_credit in list_volume:
                    break
        while True:
            try:
                defer_credit = int(input("Please enter your credits at defer : "))
            except ValueError:
                print("Integer required")
                continue
            else:
                if defer_credit not in list_volume:
                    print("Out of range")
                elif defer_credit in list_volume:
                    break
        while True:
            try:
                fail_credit = int(input("Please enter your credit at fail : "))
            except ValueError:
                    print("Integer required")
                    continue
            else:
                if fail_credit not in list_volume:
                        print("Out of range")
                elif fail_credit in list_volume:
                        break
        while True:
            total_credit = pass_credit + defer_credit + fail_credit
            if total_credit != 120:
                print("Total incorrect")
                break
            else:
                outcomes(pass_credit,defer_credit)
                break
       
    input_validation()#Calling the function
    

    #Part C & D
    
    #Prompts the user to add another student details
    print("Would you like to enter another set of data ?")
    results = input("Enter 'y' for yes or 'q' for quit and view results : ")

    if results.lower() == 'q':#breaks the loop and prints the histogram after user enters 'q' otherwise goes to else block and continue to loop again 
        histogram(progress,trailer,retriever,exclude)
        break
    else:
        continue 



        

 

    
    
        
        

    
        
    

    


