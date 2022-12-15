My program is designed to run on two matrices only.
Upon running the code, the user is prompt to enter 
the size of these matrices. If you are trying to
multiply two 512x512 matrices, you need to enter
the number 9 as your matrices are of size 2^9. 
If the wrong size is entered, the code will not work. My
code reads in a txt file of matrices that are separated
by commas. The only thing in the txt file should be the
values for the matrices. The first half of the txt
file is the first matrix, the second half is the second matrix.
My code then asks you which algorithm you want to run.
Type 1 for Strassen's, 2 for Brute Force, or 3 for combined.
If you type 3 for combined, you will need to enter
the cutoff point of n, where 2^n is the size you want
to switch the algorithms. If you want to see the output (the answer matrix), 
remove the "//" from lines 119-121. Otherwise, the program will only 
output the time ran in nanoseconds. 