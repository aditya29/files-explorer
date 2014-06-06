files-explorer
==============
Input.txt would look something like this (list of filepaths):
testingDir
testingDir/doc1
testingDir/doc2
testingDir/dir1
testingDir/dir1/doc1.1
testingDir/dir1/doc1.2
testingDir/dir1/doc1.3
testingDir/dir3
testingDir/dir2
testingDir/dir2/dir2.1
testingDir/dir2/dir2.1/doc2.1.1
testingDir/dir2/doc2.1
testingDir/dir2/doc2.2
testingDir/doc3
testingDir/doc4

Run java -jar files-explorer.jar Input.txt

see output like:

testingDir
   doc1
   doc2
   dir1
      doc1.1
      doc1.2
      doc1.3
   dir3
   dir2
      dir2.1
         doc2.1.1
      doc2.1
      doc2.2
   doc3
   doc4
   
testingDir has these children: doc1 doc2 dir1 dir3 dir2 doc3 doc4 
doc1 has no children.
doc2 has no children.
dir1 has these children: doc1.1 doc1.2 doc1.3 
doc1.1 has no children.
doc1.2 has no children.
doc1.3 has no children.
dir3 has no children.
dir2 has these children: dir2.1 doc2.1 doc2.2 
dir2.1 has these children: doc2.1.1 
doc2.1.1 has no children.
doc2.1 has no children.
doc2.2 has no children.
doc3 has no children.
doc4 has no children.
