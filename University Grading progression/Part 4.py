#Author Name: P Namrutha
#Date : 4/12/2022

#Part 4 - Dictionary


#getting count
progress = 0
trailer = 0
retriever = 0
exclude = 0
value = None



# for collect data
data_collect = {}

#possible inputs
list_volume = [0,20,40,60,80,100,120]


#getting  values and pass to the dictanory
def add(pass_credit,defer_credit,fail_credit,value):
    temp =[]
    temp.append(value)
    temp.append(pass_credit)
    temp.append(defer_credit)
    temp.append(fail_credit)
    data_collect[student_id]= temp



# check each inputs
def outcomes(pass_credit,defer_credit,fail_credit):
    global value
    if pass_credit == 120:
        print("Progress")
        value ="Progress"
        global progress
        progress = progress +1
        add(pass_credit,defer_credit,fail_credit,value)
        
       
    elif pass_credit == 100:
        print("Progress (module trailer)")
        value ="Progress (module trailer)" 
        global trailer
        trailer = trailer +1
        add(pass_credit,defer_credit,fail_credit,value)
       
        
    elif pass_credit == 80:
        print("Do not progress - module retriever")
        value="Do not progress - module retriever"
        global retriever
        retriever = retriever +1
        add(pass_credit,defer_credit,fail_credit,value)
       
    elif pass_credit == 60:
        print("Do not progress - module reitriever")
        value="Do not progress - module retriever"
        retriever = retriever +1
        add(pass_credit,defer_credit,fail_credit,value)
        
    elif pass_credit == 40 and defer_credit ==0:
        print("Exclude")
        value = "Exclude"
        global exclude
        exclude = exclude +1
        add(pass_credit,defer_credit,fail_credit,value) 
        
    elif pass_credit == 40 and defer_credit != 0:
        print("Do not progress -  module retriever")
        value="Do not progress - module retriever"
        retriever = retriever +1
        add(pass_credit,defer_credit,fail_credit,value)
    
    elif pass_credit == 20 and defer_credit >=40:
        print("Do not progress - module retriever")
        value="Do not progress - module retriever"
      
        retriever = retriever + 1
        add(pass_credit,defer_credit,fail_credit,value)
        
    elif pass_credit == 20 and defer_credit <= 20:
        print("Exclude")
        value = "Exclude"
        exclude = exclude +1
        add(pass_credit,defer_credit,fail_credit,value)
        
    elif pass_credit == 0 and defer_credit >= 60:
        print("Do not progress - module retriever")
        value="Do not progress - module retriever"
        retriever = retriever +1
        add(pass_credit,defer_credit,fail_credit,value)
        
    elif pass_credit == 0 and defer_credit <= 40:
        
        print("Exclude")
        value = "Exclude"
        exclude = exclude +1
        add(pass_credit,defer_credit,fail_credit,value)
        
        
        
# asking inputs
while True:
    student_id = input("Enter the student_ID : ")
    if student_id in data_collect.keys():
        print("Student id is exist enter unique id ")
    else:
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
                outcomes(pass_credit,defer_credit,fail_credit)
                break
    print("Would you like to enter another set of data ?")
    results = input("Enter 'y' for yes or 'q' for quit and view results : ")
    if results.lower() == 'q':
        print("Part 4 : ")
        add(pass_credit,defer_credit,fail_credit,value)
        print(data_collect)
        break
    else:
        continue             
            

    

    
       

# if student_id in credit_result():
#     answer = input(">>>")
#     if answer != 'yes':
#         print("hello")
    

# credit_result[student_id] = credit_result
# con = input("Would you like to add another student details. Enter y for yes and q for quit")

# for key, value in credit_result.items():
#     print(f"{key}{value}")
