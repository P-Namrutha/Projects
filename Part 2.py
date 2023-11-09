#Part 2

progress = 0
trailer = 0
retriever = 0
exclude = 0

#Creating an empty list 
progress_list = []
trailer_list = []
retriever_list = []
exclude_list = []

list_volume = [0,20,40,60,80,100,120]

def outcomes(pass_credit,defer_credit,fail_credit):
    global progress
    global trailer
    global retriever
    global exclude

    global progress_list
    global trailer_list
    global retriever_list
    global exclude_list

    if pass_credit == 120:
        print("Progress")
        progress = progress +1
        progress_list.append([pass_credit,defer_credit,fail_credit])#Appending values to the end of the specific list
       
    elif pass_credit == 100:
        print("Progress (module trailer)")
        trailer = trailer +1
        trailer_list.append([pass_credit,defer_credit,fail_credit])#Appending values to the end of the specific list
        
    elif pass_credit == 80:
        print("module retriever")
        retriever = retriever +1
        retriever_list.append([pass_credit,defer_credit,fail_credit])#Appending values to the end of the specific list
       
    elif pass_credit == 60:
        print("module reitriever")
        retriever = retriever +1
        retriever_list.append([pass_credit,defer_credit,fail_credit])#Appending values to the end of the specific list
        
    elif pass_credit == 40 and defer_credit ==0:
        print("Exclude")
        exclude = exclude +1
        exclude_list.append([pass_credit,defer_credit,fail_credit])#Appending values to the end of the specific list
        
    elif pass_credit == 40 and defer_credit != 0:
        print("module retriever")
        retriever = retriever +1
        retriever_list.append([pass_credit,defer_credit,fail_credit])#Appending values to the end of the specific list
    
    elif pass_credit == 20 and defer_credit >=40:
        print("module retriever")
        retreiver = retreiver + 1
        retriever_list.append([pass_credit,defer_credit,fail_credit])#Appending values to the end of the specific list
        
    elif pass_credit == 20 and defer_credit <= 20:
        print("Exclude")
        exclude = exclude +1
        exclude_list.append([pass_credit,defer_credit,fail_credit])#Appending values to the end of the specific list
        
    elif pass_credit == 0 and defer_credit >= 60:
        print("module retriever")
        retriever = retriever +1
        retriever_list.append([pass_credit,defer_credit,fail_credit])#Appending values to the end of the specific list

        
    elif pass_credit == 0 and defer_credit <= 40:
        print("Exclude")
        exclude = exclude +1
        exclude_list.append([pass_credit,defer_credit,fail_credit])#Appending values to the end of the specific list
        
        

def histogram(progress,trailer,retriever,exclude):
    star = "*"
    print(" _ _ _  _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _")
    print("Histogram")
    print("Progress",progress,":",star * progress)
    print("Trailer",trailer,":",star * trailer)
    print("Retreiver",retriever,":",star * retriever)
    print("Excluded",exclude,":",star * exclude)
    outcome = progress + trailer + retriever + exclude
    print("")
    print(outcome,"outcomes in total")
    print(" _ _ _  _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _")
    
#Defining the 
def list_1 (progress_list,trailer_list,retriever_list,exclude_list):
    print("Part 2 : ")
    plist = ",".join(str(x)for x in progress_list)
    tlist = ",".join(str(x) for x in trailer_list)
    rlist = ",".join(str(x) for x in retriever_list)
    elist = ",".join(str(x) for x in exclude_list)
    print("Progress - ",*plist, sep = '')
    print("Progress (module trailer) - ",*tlist,sep = '')
    print("Module retriever - ",*rlist, sep = '')
    print("Exclude - ",*elist, sep = '')
    

while True:#Infinite loop
    def input_validation():#Defining a function 
        while True:
            try:#Executes to get the credit values, if its a non-integer value it goes to except block 
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
                outcomes(pass_credit,defer_credit,fail_credit)
                break
       
    input_validation()#Calling a function

    #Part C & D

    print("Would you like to enter another set of data ?")
    results = input("Enter 'y' for yes or 'q' for quit and view results : ")

    if results.lower() == 'q':
        histogram(progress,trailer,retriever,exclude)
        list_1(progress_list,trailer_list,retriever_list,exclude_list)#Prints the list after 'q' is entered
        break
    else:
        continue 
    

#Reference
#https://www.entechin.com/how-to-print-a-list-without-square-brackets-in-python/#:~:text=use%20asterisk%20'*'%20operator%20to%20print%20a%20list%20without%20square%20brackets
#https://blog.finxter.com/how-to-print-a-list-without-brackets-in-python/#:~:text=You%20can%20print%20a%20list,str()%20built%2Din%20function.
    

    

    
