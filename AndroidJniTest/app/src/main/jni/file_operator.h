#ifndef _fs__
#define _fs__

#include <unistd.h>
#include <sys/stat.h>
#include <sys/time.h>
#include <stdlib.h>
#include <fcntl.h>

 int file_open(const char *filename, int flags);

 int file_read(int fd, unsigned char *buf, int size);

 int file_write(int fd, const unsigned char *buf, int size);

 int64_t file_seek(int fd, int64_t pos, int whence);

 int file_close(int fd);

 #endif