def Z_change(gameplay, wingame) :
    Z = (wingame*100)//gameplay
    
    if Z >= 99:
        return -1
    
    low, high = 0, gameplay
    result = gameplay

    while low <= high:
        mid = (low + high) // 2
        new_Z = (100 * (wingame + mid)) // (gameplay + mid)
        if new_Z > Z :
            high = mid - 1
            result = mid
        else :
            low = mid + 1
    
    return result
    


if __name__ == "__main__":
    X,Y = map(int,input().split())
    print(Z_change(X,Y))