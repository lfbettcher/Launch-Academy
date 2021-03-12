import React from "react"

const VolunteerTile = ({
  volunteerExperience: { organization, position, cause, start_date, end_date }
}) => {
  const formattedStartDate = new Date(start_date).toDateString()
  const formattedEndDate = new Date(end_date).toDateString()
  return (
    <div className="volunteer-tile">
      <h2>Organization: {organization}</h2>
      <h3>Position: {position}</h3>
      <h3>Duties: {cause}</h3>
      <h3>
        Time period: {formattedStartDate} - {formattedEndDate}
      </h3>
    </div>
  )
}

export default VolunteerTile
