all: program

program: program.o asm_io.o driver.c
	gcc -m32 -o program driver.c program.o asm_io.o

program.o :program.asm
	nasm -f elf32 -o program.o program.asm

asm_io.o: asm_io.asm
	nasm -f elf32 -d ELF_TYPE asm_io.asm

clean:
	rm *.o lynarr
