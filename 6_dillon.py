
"""
with open("6_input.txt", "r") as file:
    for line in file:
        line = line.strip()

        test_cases = int(line)
"""
import json
filename = '6_input.txt'

def parse(array):
    array = array.split(",")
    grade_value = {"A":4, "B":3, "C":2, "D":1}
    total = 0
    hours = 0
    for item in array:
        value = grade_value[item[0]] * int(item[1])
        total += value
        hours += int(item[1])

    return (total, hours, total / hours)


with open(filename) as file:
    test_cases = int(file.readline().strip())
    while test_cases > 0:
        school_name = file.readline().strip()
        students = int(file.readline())
        students_dict = {}

        hi_gpa, hi_hours = 0, 0
        while students > 0:
            line = file.readline().strip().split(':')
            students_dict[line[0]] = [line[1]]
            ret = parse(line[1])
            students_dict[line[0]].append(ret)

            if ret[2] > hi_gpa:
                hi_gpa = ret[2]
                hi_hours = ret[1]
                hi_name = line[0]

            elif ret[2] == hi_gpa:
                if ret[1] > hi_hours:
                    hi_gpa = ret[2]
                    hi_hours = ret[1]
                    hi_name = line[0]

            students -= 1

        print("{} = {}".format(school_name , hi_name))
        test_cases -= 1

# print(json.dumps(students_dict, indent=4))
