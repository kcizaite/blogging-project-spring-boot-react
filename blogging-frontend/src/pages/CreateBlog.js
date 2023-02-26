import { useState } from 'react'
import './Error.css'
import './CreateBlog.css'

export function CreateBlogPage(props) {
    const [headline, setHeadline] = useState("")
    const [text, setText] = useState("")
    const [error, setError] = useState(false);

    const clear = () => {
        setHeadline("")
        setText("")
    }

    const applyResult = (result) => {
        if (result.ok) {
            clear();
        }
        else {
            result.text().then(text => {
                const response = JSON.parse(text);
                setError(response.message)
            }).catch(error => {
                setError("Something bad happened: ", error);
            });
        }
    }

    const createBlog = () => {
        setError(false);
        if (!headline) {
            setError("Please complete the headline");
        } else {
            fetch('/api/v1/blogs/create-blog', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    headline,
                    text
                })
            }).then(applyResult)
        }
    }

    return (
        <div>
            <h1>New Entry</h1>
            <div>
                <label htmlFor="headline">Headline </label>
                <input
                    required
                    className={error ? 'error' : ''}
                    id="headline"
                    variant="outlined"
                    value={headline}
                    onChange={
                        (e) => setHeadline(e.target.value)
                    } />
            </div>
            <div>
                <label htmlFor="text">Text </label>
                <textarea
                    id="text"
                    value={text}
                    onChange={
                        (e) => setText(e.target.value)
                    } />
            </div>
            {error && <div className="errorText">{error}</div>}
            <div>
                <button onClick={createBlog}>Create</button>
            </div>
        </div>
    )
}