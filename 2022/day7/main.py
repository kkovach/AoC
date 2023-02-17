from typing import List


class Dir(object):
    def __init__(self, name, parent):
        self.name = name
        self.parent = parent
        self.files = []

    def add_child(self, obj):
        self.files.append(obj)

    def size(self):
        file_sizes: List[int] = [int(elm.size) for elm in self.files if
                                 isinstance(elm, File)]
        dir_sizes: List[int] = [int(elm.size()) for elm in self.files if
                                isinstance(elm, Dir)]
        return sum(file_sizes) + sum(dir_sizes)

    def has_dirs(self):
        return len([elm for elm in self.files if isinstance(elm, Dir)]) > 0

    def dirs(self):
        return [elm for elm in self.files if isinstance(elm, Dir)]

    def __str__(self):
        return "{}".format(self.name)


class File(object):
    def __init__(self, size):
        self.size = size

    def __str__(self):
        return self.size


class OS:
    def __init__(self):
        self.total = 0

    def part1(self, dirs: List[Dir]):
        for cur_dir in dirs:
            if cur_dir.has_dirs():
                self.part1(cur_dir.dirs())
            else:
                if cur_dir.size() <= 100000:
                    print("{} size is {}".format(cur_dir.name, cur_dir.size()))
                    self.total += cur_dir.size()


file1 = open('filesystem.txt', 'r')
lines = file1.readlines()

ls = 0
dirSize = 0
total = 0
cd = 0
filesystem = None
pwd = None
command = None
argument = None
for line in lines:
    print("Line: {}".format(line.strip()))
    parts = line.split(" ")
    size = parts[0].strip()
    command = parts[1].strip()
    if len(parts) >= 3:
        argument = parts[2].strip()
    print("Command {}, Argument {}".format(command, argument))

    if command == "cd":
        if argument == "/":
            filesystem = Dir("/", None)
            pwd = filesystem
        elif argument == "..":
            pwd = pwd.parent
        else:
            newDir = Dir(argument, pwd)
            pwd.add_child(newDir)
            print("Added {} to {}".format(newDir, pwd))
            for file in pwd.files:
                print("Cur file is {}".format(file))
                if isinstance(file, Dir) and file.name == argument:
                    pwd = file
                    break
    elif size.isnumeric():
        newFile = File(size)
        print("Adding new file {} to {}".format(newFile, pwd))
        pwd.add_child(newFile)

    print("Pwd: {} size is {}".format(pwd.name, pwd.size()))
    print("\n")

print("/ size is {}".format(filesystem.size()))
os = OS()
os.part1([filesystem])
print("Total is {}".format(os.total))
