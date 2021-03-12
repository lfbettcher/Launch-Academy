import React from "react"

import SkillsList from "./SkillsList"
import VolunteerExperiencesList from "./VolunteerExperiencesList"
import WorkExperiencesList from "./WorkExperiencesList"
import EducationExperiencesList from "./EducationExperiencesList"

const Resume = props => {
  return (
    <div className="small-12 small-centered columns">
      <h1 className="page-title text-center"> Jane Jupiter, Developer </h1>
      <div className="small-3 columns">
        <h3>
          <a href="#skills">Skills</a>
        </h3>
      </div>
      <div className="small-3 columns">
        <h3>
          <a href="#education">Education</a>
        </h3>
      </div>
      <div className="small-3 columns">
        <h3>
          <a href="#past-work">Past Work</a>
        </h3>
      </div>
      <div className="small-3 columns">
        <h3>
          <a href="#volunteer-experience">Volunteer Experience</a>
        </h3>
      </div>

      <div className="resume-section" id="skills">
        <SkillsList />
      </div>
      <div className="resume-section" id="education">
        <EducationExperiencesList />
      </div>
      <div className="resume-section" id="past-work">
        <WorkExperiencesList />
      </div>
      <div className="resume-section" id="volunteer-experience">
        <VolunteerExperiencesList />
      </div>
    </div>
  )
}

export default Resume
