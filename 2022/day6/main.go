package main

import (
	"fmt"
	"os"
)

// Main function
func main() {

	dat, err := os.ReadFile("input.txt")
	check(err)
	println(string(dat))
	s := string(dat)
	for i := 0; i < len(dat); i++ {
		substring := s[i : i+4]
		if unique(substring) {
			println(s[i : i+4])
			fmt.Printf("index is %d and answer is %d\n", i, i+4)
			break
		}
	}

	for j := 0; j < len(dat); j++ {
		substring := s[j : j+14]
		if unique(substring) {
			println(s[j : j+14])
			fmt.Printf("index is %d and answer is %d\n", j, j+14)
			break
		}
	}
}

func check(e error) {
	if e != nil {
		panic(e)
	}
}

func unique(arr string) bool {
	m := make(map[rune]bool)
	for _, i := range arr {
		_, ok := m[i]
		if ok {
			return false
		}

		m[i] = true
	}

	return true
}
