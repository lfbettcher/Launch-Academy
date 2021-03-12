import React from "react"

const WorkTile = ({
  workExperience: { company, position, description, start_date, end_date }
}) => {
  const formattedStartDate = new Date(start_date).toDateString()
  const formattedEndDate = new Date(end_date).toDateString()
  return (
    <div className="work-tile">
      <h2>Company: {company}</h2>
      <h3>Position: {position}</h3>
      <h3>Description: {description}</h3>
      <h3>
        Time period: {formattedStartDate} - {formattedEndDate}
      </h3>
    </div>
  )
}

export default WorkTile
