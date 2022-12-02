use std::collections::HashMap;
use std::fs::File;
use std::io::prelude::*;
use std::io::BufReader;

fn main() {

    let cals_file = File::open("calories.txt").unwrap();
    let reader = BufReader::new(cals_file);
    let mut elf_cals :HashMap<i32, i32> = HashMap::new();
    let mut index = 1;

    for line in reader.lines() {
        let l = line.unwrap();
        if l.to_string().is_empty() {
            index += 1;
        } else {
            let cals = l.parse::<i32>().unwrap();
            elf_cals.entry(index).and_modify(|index| *index += cals).or_insert(cals);
        }
    }

    let mut elf_cals_sorted: Vec<(&i32, &i32)> = elf_cals.iter().collect();
    elf_cals_sorted.sort_by(|a, b| b.1.cmp(a.1));
    println!("{:?}", elf_cals_sorted);
}