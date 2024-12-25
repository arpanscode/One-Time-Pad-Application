**DISCLAIMER: This is just for demonstration and exploration purposes. I don't claim by any means that this is production ready!**

One Time Pad -- Example App
--------------------------------
This is just a pair of JavaFx Apps that explore the idea of One-Time Pad encryption. Its a encryption technique where the cipher and the plain-text are of the same size. So to encrypt a file of 5mb you need a cipher of 5mb and a key of 5mb. - this makes it impractical in most cases. However, this is often recognised as the perfect cipher as the attacker can't understand anything, but the size of the encrypted file from the cipher.

Below is how the pair of the Apps work:

The Encryptor
-----------------
The Encryptor takes the plain text input and outputs the cipher text and the key to your desired output folder. The plain text can be a file of ANY Type.

The Decryptor
----------------------
The Decryptor takes the cipher text and the key as input and outputs the original file to your desired output folder.
