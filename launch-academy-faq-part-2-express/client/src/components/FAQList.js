import React, { useState, useEffect } from 'react'
import Question from './Question'
import FAQForm from './FAQForm'

const FAQList = props => {
  const [questions, setQuestions] = useState([])
  const [selectedQuestion, setSelectedQuestion] = useState([])
  
  const getQuestions = async () => {
    try {
      const response = await fetch("/api/v1/questions")
      if (!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`
        throw new Error(errorMessage)
      }
      const body = await response.json()
      setQuestions(body.questions)
    } catch (error) {
      console.error(`Error in fetch: ${error.message}`)
    }
  }

  useEffect(() => {
    getQuestions();
  }, [])

  const addQuestion = async (formPayload) => {
    try {
      const response = await fetch("/api/v1/questions", {
        method: "POST",
        headers: new Headers({
          "Content-Type": "application/json"
        }),
        body: JSON.stringify(formPayload)
      })
      if (!response.ok) {
        if (response.status === 422) {
          const body = await response.json()
          return setErrors(body.errors)
        } else {
          const errorMessage = `${response.status} (${response.statusText})`
          throw new Error(errorMessage)
        }
      }
      const body = await response.json()
      setQuestions([...questions, body.questions])
    } catch (err) {
      console.error(`Error in fetch: ${err.message}`)
    }
  }

  const toggleQuestionSelect = id => {
    if (id === selectedQuestion) {
      setSelectedQuestion(null)
    } else {
      setSelectedQuestion(id)
    }
  }

  const questionListItems = questions.map(question => {
    let handleClick = () => toggleQuestionSelect(question.id)

    return (
      <Question
        key={question.id}
        question={question.question}
        answer={question.answer}
        selected={selectedQuestion === question.id}
        handleClick={handleClick}
      />
    )
  })

  return (
    <div className="page">
      <h1>We Are Here To Help</h1>
      <div className="question-list">{questionListItems}</div>
      <FAQForm addQuestion={addQuestion} />
    </div>
  )
}

export default FAQList
