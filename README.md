# 3x3 Median Filter
Image processing - 3x3 Median Filter

# Algorithm Steps for the Implementation of this project:

step 0: ‐ open the input file and output file
        ‐ read the image header, the four numbers
        ‐ dynamically allocate mirrorFramedAry
        ‐ dynamically alloicate tempAry

step 1: read the input file and load onto mirrowframeAry begin at [1,1]

step 2: mirrowFramed the mirrorFramedAr

step 3: process the MirrorframedAry, from left to right and top to bottom
        begin at (1, 1)
        ‐ neighborAry <‐ loadNeighbors // get mirrorframedAry[i,j]'s 3 X 3 neighborhoods
        ‐ tempAry[i,j] <‐‐ sort neightborAry using selection sort algorithm,
          stop after the fifth smallest item is found,
          then return the fifth item
        ‐ keep tracking the newMin and newMax of tempAry

step 4: repeat step 3 until all pixels are processed

step 5: output the image header (numRows, numCols, newMin, newMax) to Median3X3Out.txt

step 6: output the tempAry, begin at [1,1], to Median3X3Out.txt

step 7: close input file and Median3X3Out.txt
