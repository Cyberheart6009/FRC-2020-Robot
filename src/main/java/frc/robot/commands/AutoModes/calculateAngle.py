import math

px_inch = 1
px_deg = 1

def getsize(xVal1, xVal2):
    xDifference = xVal2 - xVal1
    size = xDifference / px_inch
    return size

def getAngle(xVal1, xVal2):
    xDifference = xVal2 - xVal1
    angle = xDifference / px_deg
    return angle

def getDistance(size, angle):
    #tan(angle) = opp/adj
    #adj = opp / tan(angle)
    distance = size/2 /math.tan(angle/2) 

