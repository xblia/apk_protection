#include <unistd.h>
#include <sys/stat.h>
#include <sys/time.h>
#include <stdlib.h>
#include <fcntl.h>
#include "file_operator.h"


 int file_open(const char *filename, int flags)
{
    int fd;

    fd = open(filename, flags, 0666);
    if (fd == -1)
        return -1;

    return fd;
}

 int file_read(int fd, unsigned char *buf, int size)
{

    return read(fd, buf, size);
}

 int file_write(int fd, const unsigned char *buf, int size)
{

    return write(fd, buf, size);
}


 int64_t file_seek(int fd, int64_t pos, int whence)
{

    if (whence == 0x10000) {
        struct stat st;
        int ret = fstat(fd, &st);
        return ret < 0 ? -1 : st.st_size;
    }
    return lseek(fd, pos, whence);
}

 int file_close(int fd)
{

    return close(fd);
}