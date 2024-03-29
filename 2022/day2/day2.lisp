(require :uiop)

(defun display-match (o m s)
    (print (format nil "~A vs ~A = ~A" o m s)))

(defun win(m y)
  (or (and (string= m "A") (string= y "Y"))
      (and (string= m "B") (string= y "Z"))
      (and (string= m "C") (string= y "X"))))

(defun tie(m y)
  (or (and (string= m "A") (string= y "X"))
      (and (string= m "B") (string= y "Y"))
      (and (string= m "C") (string= y "Z"))))

(defun iPLay(opponent result)
  (cond ((string= result "X")
	(cond ((string= opponent "A") "C")
	      ((string= opponent "B") "A")
	      ((string= opponent "C") "B")))
	((string= result "Y")
	(cond ((string= opponent "A") "A")
	      ((string= opponent "B") "B")
	      ((string= opponent "C") "C")))
	((string= result "Z")
	(cond ((string= opponent "A") "B")
	      ((string= opponent "B") "C")
	      ((string= opponent "C") "A")))))

(defun points (choice)
  (cond ((string= choice "Y") 2)
	((string= choice "X") 1)
	((string= choice "Z") 3)))

(defun points2 (choice)
  (cond ((string= choice "B") 2)
	((string= choice "A") 1)
	((string= choice "C") 3)))

(defun main ()
  (let ((score 0))
  (loop for match in (uiop:read-file-lines "rounds.txt")
	do (let ((opponent (car(uiop:split-string match))) (me (car(cdr(uiop:split-string match)))))
	     (cond ((win opponent me) (incf score (+ 6 (points me))))
		   ((tie opponent me) (incf score (+ 3 (points me))))
		   ((and (not (win opponent me)) (not (tie opponent me))) (incf score (points me)))
		   )
	     )
	finally (print (format nil "Part 1 score ~A" score))
	)
  )
  )

(defun main2 ()
  (let ((score 0))
  (loop for match in (uiop:read-file-lines "rounds.txt")
	do (let ((opponent (car(uiop:split-string match))) (result (car(cdr(uiop:split-string match)))))
	     (cond ((string= result "Z") (incf score (+ 6 (points2 (iPlay opponent result)))))
		   ((string= result "Y") (incf score (+ 3 (points2 (iPlay opponent result)))))
		   ((string= result "X") (incf score (points2 (iPlay opponent result))))
		   )
	     )
	finally (print (format nil "Part 2 score ~A" score))
	)
  )
  )

(main)
(main2)
