use std::collections::HashMap;
use std::fs::{File, read};
use std::io;
use std::io::prelude::*;
use std::io::BufReader;


fn main() {
    let v = vec![1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25];
    println!("AoC 2022 Menu");
    let mut index = 0;

    while index < v.len() {
        println!("{}. ", v[index]);

        index += 1;
    }

    foo();
}

fn foo() {

    let mut cals_file = File::open("calories.txt").unwrap();
    let mut reader = BufReader::new(cals_file);
    let mut elf_cals :HashMap<i32, i32> = HashMap::new();
    let mut index = 1;

    for line in reader.lines() {
        let mut l = line.unwrap();
        if l.to_string().is_empty() {
            index += 1;
        } else {
            let mut cals = l.parse::<i32>().unwrap();
            elf_cals.entry(index).and_modify(|index| *index += cals).or_insert(cals);
        }
    }

    let mut elf_cals_sorted: Vec<(&i32, &i32)> = elf_cals.iter().collect();
    println!("{:?}", elf_cals_sorted);
    elf_cals_sorted.sort_by(|a, b| b.1.cmp(a.1));
    println!("{:?}", elf_cals_sorted);

    // for (key, value) in &elf_cals {
    //     println!("Elf {} has {} calories.", key, value);
    // }
}